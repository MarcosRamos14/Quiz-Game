package com.example.quizgame.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.quizgame.R
import com.example.quizgame.databinding.DialogChooseOptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseOptionDialogFragment : BottomSheetDialogFragment(R.layout.dialog_choose_option) {

    private lateinit var binding: DialogChooseOptionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogChooseOptionBinding.bind(view)
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            btnConfirm.setOnClickListener {
                dismiss()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack(R.id.homeFragment, false)
            }
        }
    }
}