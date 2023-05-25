import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentEditorDetail2Binding

class EditorDetail2Fragment : BaseFragment<FragmentEditorDetail2Binding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditorDetail2Binding {
        return FragmentEditorDetail2Binding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}