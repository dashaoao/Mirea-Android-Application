package com.example.mireaapp.app.presentation.news.tagdialog

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.mireaapp.databinding.TagSelectionBottomsheetDialogBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class TagDialogFragment : DialogFragment() {

    private val viewModel: TagDialogViewModel by viewModel()

    private val tagClickCallback = object : TagClickCallback {
        override fun onClick(tag: String) {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(KEY_SELECTED_TAG to tag)
            )
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        val binding = TagSelectionBottomsheetDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.tagList.adapter = TagAdapter(viewModel.tagList, tagClickCallback)
        binding.tagList.layoutManager = FlexboxLayoutManager(context).apply { FlexDirection.ROW }
        return dialog
    }

    companion object {
        private val TAG = TagDialogFragment::class.java.simpleName
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val KEY_SELECTED_TAG = "KEY_SELECTED_TAG"

        fun show(manager: FragmentManager) {
            val dialog = TagDialogFragment()
            dialog.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (tag: String?) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner,
                FragmentResultListener { _, result ->
                    listener.invoke(result.getString(KEY_SELECTED_TAG))
                }
            )
        }
    }
}