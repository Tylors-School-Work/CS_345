package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mastermind.databinding.ActivityMainBinding
import android.util.Log // This is for debugging

var _TAG_ = "Logging: "

/*

    TODO: Clean up all of the isVisible(), possibly have a function that just takes a boolean
    TODO: Clean up checkInput(), there's definitely a better algorithm than what I came up with
    TODO: Clean up decideOutput(), this goes along with the first TODO up above
    TODO: **********JUST CLEAN IT ALL UP!!!! You got this Tylor :) Nice job tonight!**********

*/

class MainActivity : AppCompatActivity() {

    // I'm setting up my ViewBinding with this variable
    // From what I've read this is now the standard practice compared to findViewById()
    private lateinit var binding: ActivityMainBinding
    private var answer = 0 // 0 meaning the game hasn't started yet
    private var guesses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating my XML to Kotlin code using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handles starting a new game
        binding.startBtn.setOnClickListener {
            startGame()
        }

        // Handles ending a game
        binding.stopBtn.setOnClickListener {
            endGame()
        }

        // Handles the input from the user
        binding.submitBtn.setOnClickListener {
            submitAnswer()
        }

    }

    // Most of the work is done here
    private fun submitAnswer() {
        ++guesses

        // If a game hasn't been started
        if(answer == 0) {
            Toast.makeText(this, "You have not started a game yet.", Toast.LENGTH_SHORT).show()
            binding.guessEt.text.clear()
            return
        }

        // First try: Convert both to strings and parse them out?
        // First try: And have a set of answers to display
        val ans = answer.toString()
        val input = binding.guessEt.text.toString()

        // Clearing out the EditText field right off the bat
        binding.guessEt.text.clear()

        if(ans == input) {
            binding.hintTxt1.text = "It took you $guesses try/tries to win!"
            binding.hintTxt2.text = "${input.length} correct digits in the correct position!"
            binding.userGuessTxt.text = "You guessed correctly!"

            // This is in case they win on their first try
            binding.hintTxt1.isVisible = true
            binding.hintTxt2.isVisible = true
            binding.userGuessTxt.isVisible = true
            answer = 0
            return
        }

        val counter = checkInput(input, ans)
        decideOutput(counter, input)

    }

    private fun decideOutput(counter: Counts, input: String) {
        binding.userGuessTxt.text = input
        binding.userGuessTxt.isVisible = true

        if(counter.correctCount == 0 && counter.outOfOrderCount == 0) {
            binding.hintTxt2.isVisible = false
            binding.hintTxt1.text = "No correct digits."
            binding.hintTxt1.isVisible = true
        }
        else {
            if(counter.outOfOrderCount == 0) {
                binding.hintTxt1.text = "${counter.correctCount} correct digit/s in the correct position/s."
                binding.hintTxt1.isVisible = true
                binding.hintTxt2.isVisible = false
            }
            else if(counter.correctCount == 0) {
                binding.hintTxt2.text = "${counter.outOfOrderCount} correct digit/s in the incorrect position/s."
                binding.hintTxt1.isVisible = false
                binding.hintTxt2.isVisible = true
            }
            else {
                binding.hintTxt1.text = "${counter.correctCount} correct digit/s in the correct position/s."
                binding.hintTxt2.text = "${counter.outOfOrderCount} correct digit/s in the incorrect position/s."
                binding.hintTxt1.isVisible = true
                binding.hintTxt2.isVisible = true
            }
        }
    }

    // Basically doing all the counting needed
    // Then returning the counter object that holds both variables need
    private fun checkInput(input: String, ans: String): Counts {
        var counter = Counts(0, 0)
        for(i in input.indices) {
            if(input[i] == ans[i]) ++counter.correctCount
            else {
                for(elements in ans) if(input[i] == elements) ++counter.outOfOrderCount
            }
        }
        return counter
    }

    private fun endGame() {
        if(answer == 0) {
            Toast.makeText(this, "Your game hasn't started yet", Toast.LENGTH_SHORT).show()
            return
        }
        if(guesses == 0) {
            Toast.makeText(this, "Game ended, the answer was $answer", Toast.LENGTH_LONG).show()
            binding.userGuessTxt.isVisible = false
            binding.hintTxt1.isVisible = false
            binding.hintTxt2.isVisible = false
            answer = 0
        }
        else {
            Toast.makeText(this, "Game ended, the answer was $answer", Toast.LENGTH_LONG).show()
            binding.hintTxt1.text = "You tried $guesses time/s before giving up.."
            binding.userGuessTxt.isVisible = false
            binding.hintTxt1.isVisible = true
            binding.hintTxt2.isVisible = false
            answer = 0
        }
        binding.guessEt.text.clear()
    }

    private fun startGame() {
        binding.userGuessTxt.isVisible = false
        binding.hintTxt1.isVisible = false
        binding.hintTxt2.isVisible = false
        binding.guessEt.text.clear()
        answer = (1000..9999).random()
        Toast.makeText(this, "You have started the game, answer is $answer", Toast.LENGTH_LONG).show()
    }
}