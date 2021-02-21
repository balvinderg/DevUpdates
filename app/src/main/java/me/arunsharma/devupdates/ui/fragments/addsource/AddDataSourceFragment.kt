package me.arunsharma.devupdates.ui.fragments.addsource

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.core.base.BaseFragment
import com.dev.core.di.utils.DaggerInjectable
import com.dev.core.utils.viewBinding
import me.arunsharma.devupdates.R
import me.arunsharma.devupdates.databinding.FragmentAddDataSourceBinding
import me.arunsharma.devupdates.databinding.FragmentFeedListBinding
import me.arunsharma.devupdates.ui.MainActivity
import me.arunsharma.devupdates.ui.viewmodels.VMDataSource
import me.arunsharma.devupdates.ui.viewmodels.VMFeedList
import javax.inject.Inject


class AddDataSourceFragment : BaseFragment(R.layout.fragment_add_data_source), DaggerInjectable {

    private val binding by viewBinding(FragmentAddDataSourceBinding::bind)

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val viewModel: VMDataSource by viewModels {
        factory
    }

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun initView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.lvFetchConfig.observe(viewLifecycleOwner, {
            binding.recyclerView.adapter = DataSourceAdapter(it)
        })

        viewModel.getServices()
    }

    override fun injectDagger() {
        (activity as MainActivity).mainComponent.inject(this)
    }

    companion object {
        const val TAG = "BookmarksFragment"
        fun newInstance(): AddDataSourceFragment = AddDataSourceFragment()
    }
}
