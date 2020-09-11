package com.aabusabra.hizligeliyodemo.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aabusabra.hizligeliyodemo.R;
import com.aabusabra.hizligeliyodemo.adapter.AllProductsAdapter;
import com.aabusabra.hizligeliyodemo.comm.ProductResult;
import com.aabusabra.hizligeliyodemo.model.MessageEvents;
import com.aabusabra.hizligeliyodemo.tasks.GetAllProductsTask;
import com.aabusabra.hizligeliyodemo.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private String TAG = HomeFragment.class.getSimpleName();
    private Context mContext;
    private View currentView;
    Unbinder unbinder;
    private Activity currentActivity;

    private Animation animationUp, animationDown;
    private AllProductsAdapter adapter;
    private List<ProductResult> allProducts = new ArrayList<>();
    private List<ProductResult> filteredAllProducts = new ArrayList<>();



    private RecyclerView productList;
    private EditText searchEdittxt;
    private View noDataFoundContainer;


    private String filteredtxt;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = root.getContext();
        currentActivity = getActivity();
        unbinder = ButterKnife.bind(this, root);
        Utils.overrideFonts(mContext, root);



        allProducts = new ArrayList<>();
        filteredAllProducts = new ArrayList<>();
        filteredtxt = "";


        productList = root.findViewById(R.id.dataList);
        searchEdittxt = root.findViewById(R.id.searchBytxt);
        noDataFoundContainer = root.findViewById(R.id.no_dataContainer);



        searchEdittxt.clearFocus();


        //animations
        animationUp = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);

        adapter = new AllProductsAdapter(mContext, allProducts, animationUp,animationDown);


        RecyclerView.LayoutManager LM;
        LM = new GridLayoutManager(mContext, 2);

        productList.setLayoutManager(LM);
        productList.setItemAnimator(new DefaultItemAnimator());
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(mContext.getDrawable(R.drawable.recycleview_divider));
//        driversList.addItemDecoration(itemDecorator);
        productList.scheduleLayoutAnimation();
        productList.setItemViewCacheSize(25);
        productList.setHasFixedSize(true);
        productList.setAdapter(adapter);
        productList.invalidate();




        searchEdittxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        //done typing
                        filteredtxt = searchEdittxt.getText().toString().trim();
                        setData();

                        return true;
                    }
                }
                return false;
            }
        });

        searchEdittxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus)
                {
                    filteredtxt = searchEdittxt.getText().toString().trim();
                    setData();
                }

            }
        });


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();

        GetAllProductsTask task = new GetAllProductsTask(mContext);
        new Thread(task).start();


        EventBus.getDefault().register(this);


    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataList(MessageEvents event) {

        switch (event.getType()) {
            case GET_PRODUCTS:

                setData();
                break;
            case NOT_GET_ALL_PRODUCTS:
                setData();

                break;

        }
    }




    public void setData()
    {

        allProducts = new ArrayList<>();
        filteredAllProducts = new ArrayList<>();

        adapter.clearAll();



        if (Utils.getSharedPreferences().getAllProducts() != null || Utils.getSharedPreferences().getAllProducts().size()>0)
        {
            for (int i=0;i<Utils.getSharedPreferences().getAllProducts().size();i++)
            {

                allProducts.add(Utils.getSharedPreferences().getAllProducts().get(i));

            }
        }


        if (filteredtxt.isEmpty())
        {

            adapter.addAll(allProducts);

        }else{
            for (int i=0;i<allProducts.size();i++)
            {
                if (allProducts.get(i).getCategory().contains(filteredtxt) ||
                        allProducts.get(i).getTitle().contains(filteredtxt))
                {

                    filteredAllProducts.add(allProducts.get(i));
                }
            }

            adapter.addAll(filteredAllProducts);

        }


        adapter.notifyDataSetChanged();
        productList.setAdapter(adapter);
        productList.invalidate();




        if (adapter.getItemCount() == 0)
        {
            noDataFoundContainer.setVisibility(View.VISIBLE);
            productList.setVisibility(View.GONE);
        }else if (adapter.getItemCount() >0)
        {
            noDataFoundContainer.setVisibility(View.GONE);
            productList.setVisibility(View.VISIBLE);
        }




    }
}