package ba.etf.rma23.projekat

import android.content.pm.ActivityInfo
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma23.projekat.HomeActivity
import com.example.videogame.R
import org.hamcrest.Matcher
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnEspressoTests {

    @get:Rule
    var homeRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    /**
     * Testiranje rasporeda u GameDetails fragmentu
     *
     * U ovom testu najprije testiramo da li se svi elementi nalaze na odgovarajućem mjestu
     *
     * Nakon toga testiramo da li su postavljeni u odgovarajućem redoslijedu
     */
    @Test
    fun PrviTest() {
        onView(withId(R.id.game_list)).perform(click())
        //da li se svi nalaze na odgovarajućem mjestu
        onView(withId(R.id.game_title_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.cover_imageview)).check(matches(isDisplayed()))
        onView(withId(R.id.platform_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.esrb_rating_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.developer_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.publisher_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.description_textview)).check(matches(isDisplayed()))

        //redoslijed

        //da li se platform_textview nalazi ispod cover_imageview
        onView(withId(R.id.platform_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.platform_textview)).check(isCompletelyDisplayed())
        onView(withId(R.id.cover_imageview)).check(isCompletelyDisplayed())
        onView(withId(R.id.platform_textview)).check(isBelow(withId(R.id.cover_imageview)))

        //da li se publisher_textview nalazi ispod developer_textview
        onView(withId(R.id.publisher_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.publisher_textview)).check(isCompletelyDisplayed())
        onView(withId(R.id.developer_textview)).check(isCompletelyDisplayed())
        onView(withId(R.id.publisher_textview)).check(isBelow(withId(R.id.developer_textview)))

    }

    /**
     * Testiranje BottomNavigationView
     * U ovom testu testiramo osnovne funkcionalnosti BottomNavigationView-a
     * najprije se testira početni zahtjev, tj. da je navigation podesen na enabled = false ukoliko jos nijedna igrica nije otvorena
     *
     * otvaramo igricu koja nas preabcuje na gameDetails fragment, zatim se vracamo na home_fragment putem klika na HOME u navigationu
     * u home fragmentu klikom na DETAILS testiramo da li se otvara posljednja zatvorena igrica
     */
    @Test
    fun DrugiTest() {
        val homeNavigation = onView(withId(R.id.homeItem))
        val detailsNavigation = onView(withId(R.id.gameDetailsItem))

        homeNavigation.check(matches(not(isEnabled())))
        detailsNavigation.check(matches(not(isEnabled())))

        onView(withId(R.id.game_list)).perform(click())
        onView(withId(R.id.homeItem)).perform(click())

        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withId(R.id.game_title_text_view)).check(matches(withText("World of Tanks")))

    }

    /**
     * Testiranje aplikaicije u landscape orijentaciji
     *
     *najprije prebacujemo orijentaciju u landscape orijentaciju
     *
     * testira se da li se prikazuje home fragment
     *
     * testira se da li se prikazuje gameDetails fragment
     */

    @Test
    fun TreciTest() {

        homeRule.scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }


            // provjera da li se prvi fragment prikazuje
            onView(withId(R.id.home_fragment)).check(matches(isDisplayed()))

            // provjera da li se drugi fragment prikazuje
            onView(withId(R.id.details_fragment)).check(matches(isDisplayed()))


    }




}

private fun Any.check(completelyDisplayed: Matcher<View>?) {


}
