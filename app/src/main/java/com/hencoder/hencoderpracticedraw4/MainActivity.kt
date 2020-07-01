package com.hencoder.hencoderpracticedraw4

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import java.util.*

class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var pager: ViewPager? = null
    var pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager = findViewById(R.id.pager) as ViewPager
        pager!!.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.Companion.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        tabLayout!!.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    inner class PageModel internal constructor(@field:LayoutRes @param:LayoutRes var sampleLayoutRes: Int, @field:StringRes @param:StringRes var titleRes: Int, @field:LayoutRes @param:LayoutRes var practiceLayoutRes: Int)

    init {
        pageModels.add(PageModel(R.layout.sample_clip_rect, R.string.title_clip_rect, R.layout.practice_clip_rect))
        pageModels.add(PageModel(R.layout.sample_clip_path, R.string.title_clip_path, R.layout.practice_clip_path))
        pageModels.add(PageModel(R.layout.sample_translate, R.string.title_translate, R.layout.practice_translate))
        pageModels.add(PageModel(R.layout.sample_scale, R.string.title_scale, R.layout.practice_scale))
        pageModels.add(PageModel(R.layout.sample_rotate, R.string.title_rotate, R.layout.practice_rotate))
        pageModels.add(PageModel(R.layout.sample_skew, R.string.title_skew, R.layout.practice_skew))
        pageModels.add(PageModel(R.layout.sample_matrix_translate, R.string.title_matrix_translate, R.layout.practice_matrix_translate))
        pageModels.add(PageModel(R.layout.sample_matrix_scale, R.string.title_matrix_scale, R.layout.practice_matrix_scale))
        pageModels.add(PageModel(R.layout.sample_matrix_rotate, R.string.title_matrix_rotate, R.layout.practice_matrix_rotate))
        pageModels.add(PageModel(R.layout.sample_matrix_skew, R.string.title_matrix_skew, R.layout.practice_matrix_skew))
        pageModels.add(PageModel(R.layout.sample_camera_rotate, R.string.title_camera_rotate, R.layout.practice_camera_rotate))
        pageModels.add(PageModel(R.layout.sample_camera_rotate_fixed, R.string.title_camera_rotate_fixed, R.layout.practice_measure_text))
        pageModels.add(PageModel(R.layout.sample_camera_rotate_hitting_face, R.string.title_camera_rotate_hitting_face, R.layout.practice_camera_rotate_hitting_face))
        pageModels.add(PageModel(R.layout.sample_flipboard, R.string.title_flipboard, R.layout.practice_flipboard))
    }
}