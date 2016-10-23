class CreateUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :users do |t|
      t.string :name
      t.string :profession
      t.string :interest
      t.string :gender
      t.date :date_of_birth
      t.boolean :swedish_speaker
      t.boolean :searching_for_match, :default => false

      t.string :auth_token

      t.timestamps
    end
  end
end
