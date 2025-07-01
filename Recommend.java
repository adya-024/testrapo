import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;
import java.util.List;

public class Recommend {

    public static void main(String[] args) {
        try {
            // Step 1: Load data
            DataModel model = new FileDataModel(new File("data/ratings.csv"));

            // Step 2: Define similarity metric
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Step 3: Define neighborhood
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Step 4: Build recommender
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Step 5: Get recommendations for a user
            int userID = 1;
            List<RecommendedItem> recommendations = recommender.recommend(userID, 3);

            System.out.println("Recommendations for user " + userID + ":");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item: " + recommendation.getItemID() + ", Value: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
