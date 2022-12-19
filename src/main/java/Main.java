import io.rollout.client.ConfigurationFetchedHandler;
import io.rollout.client.FetcherResults;
import io.rollout.client.FetcherStatus;
import io.rollout.flags.RoxStringBase;
import io.rollout.rox.server.Rox;
import io.rollout.rox.server.RoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Initialize container class that we created earlier
        Flags flags = new Flags();

        // Register the flags container with Rollout
        Rox.register(flags);

        // Building options
        RoxOptions options = new RoxOptions.Builder()
                .withConfigurationFetchedHandler(new ConfigurationFetchedHandler() {
                    @Override
                    public void onConfigurationFetched(FetcherResults fetcherResults) {
                        if (fetcherResults != null) {
                            FetcherStatus status = fetcherResults.getFetcherStatus();
                            // configuration loaded from network, flags value updated
                            if (status != null && status == FetcherStatus.AppliedFromNetwork) {
                                System.out.println("flags value updated");
                            }
                        }
                    }
                })
                .build();
        // Setup the Rollout environment key - UPDATE KEY FOR GIVEN ENV HERE
        Rox.setup("", options).get();

        ArrayList<RoxStringBase> roxFlags = (ArrayList<RoxStringBase>) Rox.getFlags();
//        Rox.setCustomStringProperty("tokenId", "123");


        // Boolean flag example
        if (Rox.dynamicAPI().isEnabled("enableTutorial", false)) {
            // TODO:  Put your code here that needs to be gated
            for (int j = 0; j < 10; j++) {
                Thread.sleep(1300);
//                String titleColor = Rox.dynamicAPI().getValue("titleColors", "null");
//                System.out.printf("DynamicApi - Title color is %s \n", titleColor);

                String titleColor2 = flags.titleColors.getValue();
                System.out.printf("Flags - Title color is %s \n", titleColor2);
            }
        }

        // String flag example
        String titleColor = flags.titleColors.getValue();
        System.out.printf("Title color is %s ", titleColor);

                // Integer flag example
        int titleSize = flags.titleSize.getValue();
        System.out.printf("Title size is %d ", titleSize);

                // Double flag example
        double specialNumber = flags.specialNumber.getValue();
        System.out.printf("Special number is %f ", specialNumber);

                // Enum flag example
                Flags.Color color = flags.titleColorsEnum.getValue();
        System.out.printf("Enum color is %s ", color.name());
    }
}