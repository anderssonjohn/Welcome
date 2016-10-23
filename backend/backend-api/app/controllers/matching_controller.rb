class MatchingController < ApplicationController
  before_action :authenticate

  def get_match

    match = User.different_language(@user).same_job(@user).where(:searching_for_match => true)


    if match.present?
      match.each do |m|
        if not Conversation.between(@user, m).present?
          Conversation.create!(sender_id: @user.id, recipient_id: m.id)

          @user.searching_for_match = false
          @user.save!

          m.searching_for_match = false
          m.save!

          render status: :ok
          return # Stop execution of this method as a match is found
        end
      end
    end

    @user.searching_for_match = true
    @user.save!

    render plain: "no match"
  end
end
