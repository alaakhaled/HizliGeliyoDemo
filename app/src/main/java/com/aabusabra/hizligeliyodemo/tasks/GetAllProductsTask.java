package com.aabusabra.hizligeliyodemo.tasks;

import android.content.Context;
import android.util.Log;

import com.aabusabra.hizligeliyodemo.comm.ProductResult;
import com.aabusabra.hizligeliyodemo.comm.RestUtils;
import com.aabusabra.hizligeliyodemo.comm.ResultLists.ProductResultList;
import com.aabusabra.hizligeliyodemo.model.MessageEvents;
import com.aabusabra.hizligeliyodemo.model.MessageTypes;
import com.aabusabra.hizligeliyodemo.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class GetAllProductsTask extends BaseTask<ProductResultList> {


    private static final String TAG = GetAllProductsTask.class.getSimpleName();
    private Gson gson;
    private Context context;
    private List<ProductResult> products = new ArrayList<>();


    public GetAllProductsTask(Context context) {
        this.context = context;

    }


    @Override
    public void run() {
        ProductResultList result = getResult();
        postResult(result);


        //recommit it if you have token needs to validate it in app
//        if (!postResult(result)) {
//            if (result != null
//                    && result.getStatus() == ErrorCodes.TOKEN_INVALID ) {
//                boolean isRefreshed = RefreshTokenTask.refreshToken();
//                if (isRefreshed) {
//                    result = getResult();
//                    postResult(result);
//                } else {
//                    RefreshTokenTask.loginFailed();
//                }
//            }
//        }
    }

    @Override
    public ProductResultList getResult() {
        Map<String, Object> map = new HashMap<>();

        ProductResultList result = null;
        products = new ArrayList<>();

        try {

            String json = RestUtils.GetJsonAppKey(RestUtils.URL_GET_PRODUCTS, null, "");
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ProductResult>>(){}.getType();
            products = gson.fromJson(json, listType);

        } catch (Exception e) {
            Utils.sendExceptionEvent(GetAllProductsTask.class, e);
            Log.e(TAG, e.getMessage() + "");
        }
        return result;
    }

    @Override
    public boolean postResult(ProductResultList result) {
        if (products != null) {


            Utils.getSharedPreferences(context).clearAllProducts();

            for (ProductResult p : products) {

                Utils.getSharedPreferences(context).addProduct(p);

            }
            MessageEvents event = new MessageEvents(MessageTypes.GET_PRODUCTS);
            EventBus.getDefault().post(event);
            return true;
        }else {
            MessageEvents event = new MessageEvents(MessageTypes.NOT_GET_ALL_PRODUCTS);
            EventBus.getDefault().post(event);

        }

        return false;
    }
}