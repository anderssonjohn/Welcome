Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  get 'user' => 'users#show'

  post 'user' => 'users#create'

  get 'match' => 'matching#get_match'

  get 'conversations' => 'conversations#get'

  get 'delete/:id' => 'conversations#delete'

  # :id is the id of the recipient
  get 'conversations/:id/messages' => 'conversations#get_messages'

  post 'messages' => 'messages#create'

  post 'update_profession' => 'users#update_profession'

  # resources :conversations do
  #   resources :messages
  # end
end
