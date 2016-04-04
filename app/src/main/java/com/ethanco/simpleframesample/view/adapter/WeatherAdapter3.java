//package com.ethanco.simpleframesample.view.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.ethanco.simpleframe.view.adapter.SimpleAdapter;
//import com.ethanco.simpleframesample.R;
//import com.ethanco.simpleframesample.bean.Weather;
//import com.ethanco.simpleframesample.databinding.ItemWeatherBinding;
//
//import java.util.List;
//
///**
// * Created by EthanCo on 2016/4/4.
// */
//public class WeatherAdapter3 extends SimpleAdapter<WeatherAdapter3.ItemViewHodler> {
//
//    private List<Weather.ResultEntity.DataEntity.WeatherEntity> weatherList;
//
//    public WeatherAdapter3(List<Weather.ResultEntity.DataEntity.WeatherEntity> weatherList) {
//        this.weatherList = weatherList;
//    }
//
//    @Override
//    public ItemViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
//        ItemViewHodler viewHodler = new ItemViewHodler(view);
//        return viewHodler;
//    }
//
//    @Override
//    public void onBindViewHolder(ItemViewHodler holder, int position) {
////        holder.tvDate.setText(weatherList.get(position).getDate());
//        holder.bind(weatherList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return weatherList.size();
//    }
//
//    class ItemViewHodler extends RecyclerView.ViewHolder {
//        private final ItemWeatherBinding binding;
//
////        TextView tvDate;
//
//        public ItemViewHodler(View itemView) {
//            super(itemView);
////            tvDate = (TextView) itemView.findViewById(R.id.tvWeatherDate);
//            binding = ItemWeatherBinding.bind(itemView);
//        }
//
//        public void bind(Weather.ResultEntity.DataEntity.WeatherEntity weatherEntity) {
//            binding.setWeatherEntiry(weatherEntity);
//        }
//    }
//}
