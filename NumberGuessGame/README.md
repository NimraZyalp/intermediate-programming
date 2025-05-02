# NumberGuessGame Test Cases

#### 1. Test Case: Valid Range Input
- Description: The program should take a valid input of range and select a target number within the range.
- Steps:
  1. Input `10` as the range start.
  2. Input `50` as the range end.
  3. Make a guess within the range.
- Behavior to test for: The game is to supply a random target number between 10 and 50 and to provide messages like "Too low!" or "Too high!" regarding the guesses.

---
#### 2. Test Case: Input invalid Range
- Description: Verify the program asks for re-input of range if start greater than or equal to end
- Steps:
 1. As range start input, enter `50`.
2. As the range's end, provide `10`.
3. In the second prompt, type a valid range (`10` as the start, `50` as the end).
- Expected Behavior: Program should reject the invalid range and accept the valid on the second try.

---
Test Case: Too Low Guess
- Description: Check if program gives feedback for too low of a guess.
- Steps:
1. Enter a valid range, say, from 10 to 50.
2. Enter a guess, for example 15, which is lower than the required number 25.
Expected behavior: The program gives a response, "Too low!" and requests another guess.

---

#### 4. Test Case: Too High Guessing
Description: Program shall give response when a guess is too high.
Steps:
1. Enter valid range, say from 10 to 50.
2. Enter any guess that is higher than the target. Example 35 when the required target number is 25.
- Expected Behavior: Program would print "Too high!" and ask for another guess.

---

### 5. Correct Guess Test Case
- Description: Check whether program will exit game and flash correct message upon correctly guessing the number.
- Steps
1. Input a valid range; between 10 and 50, for instance.
2. Keep inputting guesses of numbers until the correct target number is guessed.
- Expected Result:
- The program should print out "Congratulations!" with the number of attempts it had taken and the total time taken.

---
 
#### 6. Test Case: Hint System
- Description: The hint system should turn on after a certain threshold of guesses have gone wrong.
- Steps:
1. Enter a valid range, say between 10 and 50.
  2. Enter incorrect guesses up to a certain amount to trigger the hint system, say 5 guesses.
- Expected Behavior: A programme hint, such as "You're close" or "You're far from the target number."

---

#### 7. Test Case: High Score Update
- Description: The program to update the high score once one player beats the record.
- Steps:
1. Insert a valid range, say between 10 and 50.
2. Play the game, attempting to guess the number in less tries than recorded by the high score.
- Expected Behavior: Program should record and show new high score for the range.

---

#### 8. Replay Option
- Description: Ensure that when the play again is chosen, the program restarts successfully.
- Steps:
1. Play a successful game by guessing the number.
  2. When prompted, enter "yes".
3. Start a new game, giving a new range, say between 1 and 100.
- Behavior expectation: The program should start afresh with a new random target number in the new range.

---

#### 9. Test Case: Range or Guess Containing Invalid Input
Description: Test the program on how it handles non-integer inputs for the range and the guess. 
Steps:
Instructions: 
1. Type in a string instead of an integer for the range input, "abc".
2. Type in a string instead of an integer for the guess input, "xyz". 
- Expected Behavior: Program recognises the input to be wrong and prompts the user to input a valid integer and it should request that input again.

