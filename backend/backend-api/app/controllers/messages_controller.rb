class MessagesController < ApplicationController
  # include MessagesHelper
  before_action :authenticate

  def create
    conversation = Conversation.between(@user.id, params[:recipient]).first
    message = conversation.messages.build(body: params[:body], user_id: @user.id)

    message.save!

    render status: 200
  end

  private

  # def message_params
  #   params.require(:message).permit(:body)
  # end

  # def self_or_other(message)
  #   message.user == current_user ? "self" : "other"
  # end

  # def message_interlocutor(message)
  #   message.user == message.conversation.sender ? message.conversation.sender : message.conversation.recipient
  # end

end
