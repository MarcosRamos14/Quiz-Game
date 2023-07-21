package com.example.quizgame.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentDialogBinding
import com.example.quizgame.ui.extensions.showShortToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InputNameDialogFragment : BottomSheetDialogFragment(R.layout.fragment_dialog) {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setBackgroundDrawableResource(R.color.black_opacity)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDialogBinding.bind(view)
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            btnPlay.setOnClickListener {
                if (editNickName.text.toString().isEmpty()) {
                    showShortToast(R.string.warning_toast_nickname)
                } else {
                    findNavController().navigate(R.id.action_dialogFragment_to_quizFragment)
                }
            }
        }
    }
}