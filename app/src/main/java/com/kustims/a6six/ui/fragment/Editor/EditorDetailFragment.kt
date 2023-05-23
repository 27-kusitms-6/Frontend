import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentEditorDetailBinding
import com.kustims.a6six.databinding.FragmentPlayRecommendationBinding

class EditorDetailFragment : BaseFragment<FragmentEditorDetailBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditorDetailBinding {
        return FragmentEditorDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}