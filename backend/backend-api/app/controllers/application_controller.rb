class ApplicationController < ActionController::API
  include ActionController::HttpAuthentication::Token::ControllerMethods
  def authenticate
    authenticate_or_request_with_http_token do |token, options|
      @user = User.find_by(auth_token: token)
    end
  end

end
