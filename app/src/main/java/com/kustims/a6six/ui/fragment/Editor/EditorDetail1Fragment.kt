import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentEditorDetail1Binding

class EditorDetail1Fragment : BaseFragment<FragmentEditorDetail1Binding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditorDetail1Binding {
        return FragmentEditorDetail1Binding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}