package com.dev.lishabora.Views.Trader.Activities

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.Toolbar
import com.dev.foodmovers.R
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class DrawerClass {

    private fun getBitmap(activity: Activity, img: String): Bitmap {


        val thumnail = BitmapFactory.decodeResource(activity.resources, R.drawable.ic_launcher_background)

        try {

            return thumnail

        } catch (nm: Exception) {
            return thumnail
        }


    }

    companion object {

        fun getDrawer(email: String?, name: String?, activity: Activity, toolbar: Toolbar, itemListener: DrawerItemListener) {
            lateinit var result: Drawer
            val drawerEmptyItem = PrimaryDrawerItem().withIdentifier(0).withName("")
            drawerEmptyItem.withEnabled(false)


            val home = PrimaryDrawerItem().withIdentifier(1)
                    .withName("Home").withTextColorRes(R.color.white)
                    .withIcon(R.drawable.ic_home_black_24dp)


//            val profile = PrimaryDrawerItem().withIdentifier(2)
//                    .withName("Profile").withTextColorRes(R.color.white).withIcon(R.drawable.ic_account)
            val profile = PrimaryDrawerItem().withIdentifier(2)
                    .withName("Profile").withTextColorRes(R.color.white).withIcon(R.drawable.ic_account_circle_black_24dp)

            val logout = PrimaryDrawerItem().withIdentifier(3)
                    .withName("Log Out").withTextColorRes(R.color.white).withIcon(R.drawable.ic_exit_to_app_black_24dp)


            val about = PrimaryDrawerItem().withIdentifier(4)
                    .withName("About").withTextColorRes(R.color.white).withIcon(R.drawable.ic_info_24dp)


            val share = PrimaryDrawerItem().withIdentifier(5)
                    .withName("Share").withTextColorRes(R.color.white)
                    .withIcon(R.drawable.ic_share_black_24dp)//.withBadge("3")


            val headerResult = AccountHeaderBuilder()
                    .withActivity(activity)
                    .withOnProfileClickDrawerCloseDelay(2)
                    .withTextColorRes(R.color.white)
                    .withSelectionListEnabledForSingleProfile(false)
                    .withDividerBelowHeader(true)

                    //  .withHeaderBackground(R.drawable.dvf)
                    .addProfiles(
                            ProfileDrawerItem().withName(name).withEmail(email)

                                    //.withSelectedTextColorRes(R.color.colorPrimaryDark)
                                    .withIcon(R.drawable.foodmovers)


                    )
                    //.withOnAccountHeaderListener { view, profile, currentProfile -> false }
                    .build()


            result = DrawerBuilder()

                    .withAccountHeader(headerResult)
                    .withActivity(activity)
                    //.withFooter(R.layout.footer)

                    //.withFooter(R.layout.footer)

                    //.withGenerateMiniDrawer(true)
                    .withFooterDivider(false)

                    .withSelectedItem(1)

                    .withToolbar(toolbar)
                    .withSliderBackgroundColorRes(R.color.colorPrimary)

                    // .withSliderBackgroundDrawableRes(R.drawable.headermain)

                    //.withAccountHeader(headerResult)
                    .withActionBarDrawerToggle(true)
                    .withActionBarDrawerToggleAnimated(true)
                    .withCloseOnClick(true)
                    //.withSelectedItem(-1)
                    .addDrawerItems(
                            home,
                            profile,
                            DividerDrawerItem(),
                            logout, about, share


                    )
                    .withFooterClickable(true)
                    //.withStickyFooter(R.layout.footer)


                    .withOnDrawerItemClickListener { view, position, drawerItem ->

                        when (drawerItem.identifier.toInt()) {
                            1 -> {
                                itemListener.homeClicked()
                                result.closeDrawer()
                            }

                            2 -> {
                                itemListener.profileClicked()
                                result.closeDrawer()
                            }

                            3 -> {
                                itemListener.logOutClicked()
                                result.closeDrawer()
                            }
                            4 -> {
                                itemListener.helpClicked()
                                result.closeDrawer()
                            }
                            5 -> {
                                itemListener.shareClicked()
                                result.closeDrawer()
                            }


                        }
                        true
                    }

                    .build()
        }
    }
}