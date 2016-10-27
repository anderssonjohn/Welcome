class MessagesController < ApplicationController
  # include MessagesHelper
  before_action :authenticate

  def create
    conversation = Conversation.between(@user.id, params[:recipient]).first
    message = conversation.messages.build(body: params[:body], user_id: @user.id)

    message.save!

    render status: 200
  end
end
