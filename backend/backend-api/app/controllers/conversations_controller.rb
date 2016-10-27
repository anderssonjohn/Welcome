class ConversationsController < ApplicationController
  before_action :authenticate

  def get
    conversation = Conversation.involving(@user)
    conversation.each do |conv|
      conv.recipient_id = interlocutor(conv).id
    end
    render :json => conversation.to_json(:methods => :name)
  end

  def delete
    Conversation.between(@user, User.find(params[:id])).first.destroy
  end

  def get_messages
    # :id is the id of the recipient
    conversation = Conversation.between(@user.id, params[:id]).first
    render :json => conversation.messages
  end

  private

  def interlocutor(conversation)
    @user == conversation.recipient ? conversation.sender : conversation.recipient
  end
end
