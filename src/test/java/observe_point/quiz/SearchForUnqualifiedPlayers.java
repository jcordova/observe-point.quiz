package observe_point.quiz;

import com.cwjcsu.joutable.DefaultTable;
import com.cwjcsu.joutable.DefaultTableFormatter;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class SearchForUnqualifiedPlayers {

    String endpoint = CoreConstant.RAPID_API_ENDPOINT;

    @Test
    @DisplayName("Search for unqualified game players.")
    @Description("Check that in each game best player has to weigh more than 200 pounds, height more than 5 feet, 10 inches.")
    public void search_for_unqualified_game_players() {

        Allure.step("Validate Response's status code");
        Validate.response_status_code(endpoint, 200);

        Allure.step("Validate Response's body");
        Validate.response_body_not_empty(endpoint);

        Allure.step("Search for Unqualified Players based on Weight and Height");
        assertFalse("Not all Players qualified for the Games.", check_for_player_eligibility());
    }

    @Step
    private Boolean check_for_player_eligibility() {

        DefaultTable dt = new DefaultTable();

        JsonPath jsonPathEvaluator = RestApiClient.responseAsJsonPath(endpoint);
        List<Integer> game_id = jsonPathEvaluator.get("data.game.id");
        List<Integer> team_full_name = jsonPathEvaluator.get("data.team.full_name");
        List<Integer> player_first_name = jsonPathEvaluator.get("data.player.first_name");
        List<Integer> player_last_name = jsonPathEvaluator.get("data.player.last_name");
        List<Integer> weight_pounds = jsonPathEvaluator.get("data.player.weight_pounds");
        List<Integer> height_feet = jsonPathEvaluator.get("data.player.height_feet");
        List<Integer> height_inches = jsonPathEvaluator.get("data.player.height_inches");

        int i = 0;
        search: {
            for (Integer wp : weight_pounds) {
                for (Integer hf : height_feet) {
                    for (Integer hi : height_inches) {
                        if (wp != null && !wp.toString().isEmpty() && hf != null && !hf.toString().isEmpty() && hi != null && !hi.toString().isEmpty()) {
                            if (wp <= 200 && hf <= 5 && hi <= 10) {
                                System.out.println("\n");
                                dt.setTitle("UNQUALIFIED PLAYER(s)");
                                dt.setHeaders(new String[] { "ID", "NAME", "DETAIL" });
                                dt.addRow(new Object[] { 1, "GAME ID", game_id.get(i)});
                                dt.addRow(new Object[] { 2, "TEAM's NAME", team_full_name.get(i)});
                                dt.addRow(new Object[] { 3, "PLAYER's NAME", player_first_name.get(i) + " " + player_last_name.get(i)});
                                dt.addRow(new Object[] { 4, "WEIGHT IN POUNDS", weight_pounds.get(i)});
                                dt.addRow(new Object[] { 5, "HEIGHT", height_feet.get(i) + " FEET, " + height_inches.get(i) + " INCHES" });
                                DefaultTableFormatter dtf = new DefaultTableFormatter(100, 2);
                                System.out.println(dtf.format(dt));
                                break search;
                            }
                        }
                    }
                }
                i++;
            }
        }
        return true;
    }
}
