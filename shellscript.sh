# Step a: Ask for the user's name and save it to a variable
echo "What is your name?"
read user_name

# Step b: Ask for the user's favorite flavor of ice cream and save it to another variable
echo "What is your favorite flavor of ice cream?"
read ice_cream_flavor

# Step c: Use the 'say' command in a non-default voice to welcome the user and respond to the input
say -v Daniel "Welcome, $user_name! It's great to know that your favorite ice cream flavor is $ice_cream_flavor."
