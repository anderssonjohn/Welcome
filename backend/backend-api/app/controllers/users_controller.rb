class UsersController < ApplicationController
  before_action :authenticate, only: [:show, :update_profession]

  def show
    render :json => @user
  end

  def create
    user = User.new(name: params[:name],
      profession: params[:profession],
      swedish_speaker: params[:swedish_speaker],
      gender: params[:gender],
      date_of_birth: params[:date],
      interest: params[:interest])
    if user.valid?
      user.save
      render :json => user
    else
      render status: 406
    end
  end

  def update_profession
    @user.profession = params[:profession]
    @user.save!
    render status: :ok
  end
end
