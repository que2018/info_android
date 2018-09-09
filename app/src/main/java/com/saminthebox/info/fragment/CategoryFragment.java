package com.saminthebox.info.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saminthebox.info.adapter.PriceAdapter;
import com.saminthebox.info.constant.ADDR;
import com.saminthebox.info.customview.CategoryCell;
import com.saminthebox.info.database.model.Category;
import com.saminthebox.info.database.model.Price;
import com.saminthebox.info.helper.ImageTask;
import com.saminthebox.info.network.GetNetData;
import com.saminthebox.info.R;

public class CategoryFragment extends Fragment {

    private ArrayList<Category> categories;
    private GridLayout gridLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, null);

        gridLayout = rootView.findViewById(R.id.category);

        categories = new ArrayList<Category>();

        CategoryTask categoryTask = new CategoryTask();
        categoryTask.execute();

        return rootView;
    }

    class CategoryTask extends AsyncTask<Void, Void, Void> {
        private JSONObject outdata;

        @Override
        protected Void doInBackground(Void... params) {
            outdata = GetNetData.getResult(ADDR.CATEGORY);

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                JSONObject dataJson = (JSONObject) outdata.get("data");

                JSONArray categoriesJson = dataJson.getJSONArray("categories");


                for (int i = 0; i < categoriesJson.length(); i++) {
                    JSONObject categoryJson = (JSONObject) categoriesJson.get(i);


                    String title = categoryJson.getString("title");
                    String imageUrl = categoryJson.getString("image_url");
                    //String trend = pirceCurrency.getString("trend").toString();
                    //String trendSign = pirceCurrency.getString("trend_sign").toString();

                    Category category = new Category();
                    category.setTitle(title);
                    category.setImageUrl(imageUrl);
                    categories.add(category);
                }

                    gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
                    gridLayout.setColumnCount(4);
                    gridLayout.setRowCount(3);
                    TextView titleText;
                    for (int k = 0; k < categories.size(); k++) {

                        Category category1 = categories.get(k);

                        String title1 = category1.getTitle();

                        CategoryCell categoryCell = new CategoryCell(getActivity());

                        categoryCell.setTitle(title1);

                        gridLayout.addView(categoryCell,k);
                        //titleText.setCompoundDrawablesWithIntrinsicBounds(rightIc, 0, 0, 0);
                        //GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                        //param.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        //param.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                        //param.rightMargin = 5;
                        //param.topMargin = 5;
                        //param.setGravity(Gravity.CENTER);
                        //param.columnSpec = GridLayout.spec(c);
                        //param.rowSpec = GridLayout.spec(r);
                        //titleText.setLayoutParams (param);

                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
