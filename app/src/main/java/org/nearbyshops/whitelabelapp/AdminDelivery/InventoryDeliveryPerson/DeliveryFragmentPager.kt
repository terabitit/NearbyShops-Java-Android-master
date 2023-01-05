package org.nearbyshops.whitelabelapp.AdminDelivery.InventoryDeliveryPerson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nearbyshops.whitelabelapp.Interfaces.*
import org.nearbyshops.whitelabelapp.databinding.FragmentDeliveryInventoryBinding


class DeliveryFragmentPager: Fragment(), NotifyTitleChangedWithTab, RefreshAdjacentFragment,NotifySearch {


    private var pagerAdapter: PagerAdapter? = null

    private lateinit var binding: FragmentDeliveryInventoryBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentDeliveryInventoryBinding.inflate(inflater)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = PagerAdapter(childFragmentManager)

        // Set up the ViewPager with the sections adapter.;
        binding.viewPager.adapter = pagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewPager)


        if(activity is SetToolbarText)
        {
            (activity as SetToolbarText).setToolbar(true,"Orders",false,null)
        }
    }





    override fun NotifyTitleChanged(title: String, tabPosition: Int) {
        pagerAdapter?.setTitle(title, tabPosition)
    }





    override fun refreshAdjacentFragment() {
        val fragment = childFragmentManager.findFragmentByTag(
                makeFragmentName(binding.viewPager.id, binding.viewPager.currentItem + 1)
            )
        if (fragment is RefreshFragment) {
            (fragment as RefreshFragment).refreshFragment()
        }
    }




    override fun search(searchString: String?) {

        val fragment = childFragmentManager.findFragmentByTag(
            makeFragmentName(binding.viewPager.id, binding.viewPager.currentItem + 1)
        )

        if (fragment is NotifySearch) {
            fragment.search(searchString)
        }


//        requireContext().showToast("Search $searchString")
    }


    override fun endSearchMode() {

        val fragment = childFragmentManager.findFragmentByTag(
            makeFragmentName(binding.viewPager.id, binding.viewPager.currentItem + 1)
        )

        if (fragment is NotifySearch) {
            fragment.endSearchMode()
        }

//        requireContext().showToast("End Search")
    }




    companion object {


        private fun makeFragmentName(viewId: Int, index: Int): String {
            return "android:switcher:$viewId:$index"
        }
    }

}