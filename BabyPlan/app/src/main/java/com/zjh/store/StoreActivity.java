package com.zjh.store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junweiliu.simpleindicatorview.BannerPagerAdapter;
import com.example.junweiliu.simpleindicatorview.BannerTimerTask;
import com.example.junweiliu.simpleindicatorview.IndicatorView;
import com.example.zh.babyplan.R;
import com.zjh.adapter.BabyProductHorizontalListViewAdapter;
import com.zjh.adapter.ClassifyPageExpandableListViewAdapter;
import com.zjh.adapter.DiscountListviewAdapter;
import com.zjh.adapter.HotBrandHorizontalListViewAdapter;
import com.zjh.adapter.HouseholdDailyHorizontalListViewAdapter;
import com.zjh.adapter.NewGoodsHorizontalListViewAdapter;
import com.zjh.adapter.ShoppingCartAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class StoreActivity extends FragmentActivity implements OnClickListener,ShoppingCartAdapter.CheckInterface,ShoppingCartAdapter.ModifyCountInterface{

	String username="user";
	String password="pass";

	//底部导航栏部分
	private NoScrollViewPager viewPager;//用来放置界面切换
	private PagerAdapter mPagerAdapter;//初始化View适配器
	private List<View> mViews = new ArrayList<View>();//用来存放 首页、分类、购物车、我的
	//四个线性布局，每个包含一个导航按钮
	private LinearLayout home_page;
	private LinearLayout classify_page;
	private LinearLayout shopping_cart_page;
	private LinearLayout mine_page;
	//四个按钮
	private ImageButton home_page_imagebutton;
	private ImageButton classify_page_imagebutton;
	private ImageButton shopping_cart_page_imagebutton;
	private ImageButton mine_page_imagebutton;



	//主页部分
	//图片滑动部分
	//轮播图
	private ViewPager mViewPager;
	//指示器
	private IndicatorView mIndicatorView;
	//适配器
	private BannerPagerAdapter mBannerPagerAdapter;
	//图片资源
	private List<Integer> pictureList = new ArrayList<>();
	//轮播图自动轮播消息
	public static final int AUTOBANNER_CODE = 0X1001;
	//当前轮播图位置
	private int mBannerPosition;
	//自动轮播计时器
	private Timer timer = new Timer();
	//自动轮播任务
	private BannerTimerTask mBannerTimerTask;
	//用户当前是否点击轮播图
	private boolean mIsUserTouched = false;
	//轮播图Handler
	Handler bannerHandler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			// 当用户点击时,不进行轮播
			if (!mIsUserTouched) {
				// 获取当前的位置
				mBannerPosition = mViewPager.getCurrentItem();
				// 更换轮播图
				mBannerPosition = (mBannerPosition + 1) % mBannerPagerAdapter.FAKE_BANNER_SIZE;
				mViewPager.setCurrentItem(mBannerPosition);
			}
			return true;
		}
	});

	//搜索框
	LinearLayout search_layout;
	EditText search_et;

	//新品横向列表
	HorizontalListView new_goods_horizontallistview;
	NewGoodsHorizontalListViewAdapter newgoodshorizontallistviewAdapter;

	//热门品牌横向列表
	HorizontalListView hot_brand_horizontallistview;
	HotBrandHorizontalListViewAdapter hotbrandhorizontallistviewAdapter;

	//家居日用横向列表
	HorizontalListView household_daily_horizontallistview;
	HouseholdDailyHorizontalListViewAdapter householdDailyHorizontalListViewAdapter;

	//婴童用品横向列表
	HorizontalListView baby_product_horizontallistview;
	BabyProductHorizontalListViewAdapter babyProductHorizontalListViewAdapter;

	//优惠商品列表
	//封装优惠商品数据
	private int[] discounts_ItemImage=new int[] {R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods};
	private String[] discounts_ItemName=new String[] {"帮宝适大人都喜欢的奶嘴1","帮宝适大人都喜欢的奶嘴2","帮宝适大人都喜欢的奶嘴3","帮宝适大人都喜欢的奶嘴4"};
	private String[] discounts_NewItemPrice=new String[] {"30.0","30.0","30.0","30.0"};
	private String[] discounts_OldItemPrice=new String[] {"60.0","60.0","60.0","60.0"};
	TextView discounts_goods_oldPrice;
	ListView discount_listview;
	DiscountListviewAdapter discountListviewAdapter;


	/**
	 * 购物车页面
	 */
	//购物车部分
	private int[] shopping_cart_ItemImage=new int[] {R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.goods};
	private String[] shopping_cart_ItemName=new String[] {"帮宝适大人都喜欢的纸尿裤1","帮宝适大人都喜欢的纸尿裤2","帮宝适大人都喜欢的纸尿裤3","帮宝适大人都喜欢的纸尿裤4"};
	private String[] shopping_cart_ItemAttr=new String[] {"灰色大码","白色小码","红色中码","黄色加大码"};
	private double[] shopping_cart_ItemPrice=new double[] {130.00,134.05,152.31,102.36};
	private int[] shopping_cart_ItemAmount=new int[] {2,2,3,4};
	//是否全选结算
	private static final String TAG="StoreActivity";
	//全选复选框
	CheckBox ckAll;
	//总额
	TextView showtotalPrice;
	//结算
	TextView tvSettlement;
	ListView list_shopping_cart;
	private ShoppingCartAdapter shoppingCartAdapter;
	private boolean flag=false;
	private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
	private boolean mSelect;
	private double totalPrice=0.00;//购买的商品总价
	private int totalCount=0;//购买商品的总数量



	/**
	 * 分类页面
	 */
	private ExpandableListView classify_page_expandlistview;
	private List<String> classify_page_groupnameList;
	private List<List<String>> classify_page_itemnameList;
	private ClassifyPageExpandableListViewAdapter classifypageexpandablelistviewAdapter;


	/**
	 * 我的页面
	 */
	//头像
	ImageView head_portrait;
	TextView view_all_indents_tv;//查看所有订单textview
	TextView wait_for_payment_tv;
	TextView wait_for_sending_tv;//待付款textview
	TextView wait_for_goods_tv;
	TextView wait_for_evaluate_tv;
	TextView collect_tv;
	TextView address_tv;
	TextView suggestion_feedbaack_tv;
	TextView after_sales_service_tv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.store_activity_main);
		/**
		 * 底部导航栏部分
		 */
		initView();
		initViewPage();
		initEvent();

		/*
		 * 首页图片滑动部分
		 */

		//购物车全选部分
//		ImageLoader imageLoader= ImageLoader.getInstance();
//		imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
	}

	public Handler handler=new Handler(){
		public boolean seandMessageAtTime(Message msg,long uptimeMills){

			return  super.sendMessageAtTime(msg,uptimeMills);
		}
	};


	/**
	 * 底部导航栏部分
	 */
	private void initView() {
		viewPager = (NoScrollViewPager) findViewById(R.id.id_viewpage);
		//初始化四个LinearLayout
		home_page = (LinearLayout) findViewById(R.id.home_page);
		classify_page = (LinearLayout) findViewById(R.id.classify_page);
		shopping_cart_page = (LinearLayout) findViewById(R.id.shopping_cart_page);
		mine_page = (LinearLayout) findViewById(R.id.mine_page);
		//初始化四个按钮
		home_page_imagebutton = (ImageButton) findViewById(R.id.home_page_imagebutton);
		classify_page_imagebutton = (ImageButton) findViewById(R.id.classify_imagebutton);
		shopping_cart_page_imagebutton = (ImageButton) findViewById(R.id.shopping_cart_imagebutton);
		mine_page_imagebutton = (ImageButton) findViewById(R.id.mine_imagebutton);
	}
	@SuppressLint("InflateParams")
	private void initViewPage() {
		//初始化 主页、分类、购物车、我的 布局
		LayoutInflater inflater = LayoutInflater.from(this);
		View home_page_view = inflater.inflate(R.layout.home_page, null);
		View classify_page_view = inflater.inflate(R.layout.classify_page, null);
		View shopping_cart_page_view = inflater.inflate(R.layout.shopping_cart_page_hava, null);
		View mine_page_view = inflater.inflate(R.layout.mine_page, null);
		mViews.add(home_page_view);
		mViews.add(classify_page_view);
		mViews.add(shopping_cart_page_view);
		mViews.add(mine_page_view);

		/**
		 * 主页部分
		 */
		/**
		 * 初始化数据
		 */
		pictureList.add(R.mipmap.ic_launcher);
		pictureList.add(R.mipmap.ic_launcher);
		pictureList.add(R.mipmap.ic_launcher);
		pictureList.add(R.mipmap.ic_launcher);
		pictureList.add(R.mipmap.ic_launcher);
		pictureList.add(R.mipmap.ic_launcher);
		/**
		 * 初始化控件
		 */
		mViewPager = (ViewPager) home_page_view.findViewById(R.id.vp_banner);
		mIndicatorView= (IndicatorView) home_page_view.findViewById(R.id.idv_banner);
		mBannerPagerAdapter = new BannerPagerAdapter(home_page_view.getContext(), pictureList);
		mViewPager.setAdapter(mBannerPagerAdapter);
		mIndicatorView.setViewPager(pictureList.size(), mViewPager);
		// 设置默认起始位置,使开始可以向左边滑动
		mViewPager.setCurrentItem(pictureList.size() -pictureList.size());
		mIndicatorView.setOnPageChangeListener(new IndicatorView.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			@Override
			public void onPageSelected(int position) {
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		//由于ViewPager 没有点击事件，可以通过对VIewPager的setOnTouchListener进行监听已达到实现点击事件的效果
		mViewPager.setOnTouchListener(new OnTouchListener() {
			int flage = 0 ;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()){
					case MotionEvent.ACTION_DOWN:
						flage = 0 ;
						break ;
					case MotionEvent.ACTION_MOVE:
						flage = 1 ;
						break ;
					case  MotionEvent.ACTION_UP :
						if (flage == 0) {
							int item = mViewPager.getCurrentItem();
//	                        if (item%pictureList.size() == 0) {
//	                            Intent intent=new Intent(StoreActivity.this,One.class);
//	                            startActivity(intent);
//	                        } else if (item%pictureList.size() == 1) {
//	                            Intent intent=new Intent(StoreActivity.this,Two.class);
//	                            startActivity(intent);
//	                        } else if (item%pictureList.size() == 2) {
//	                            Intent intent=new Intent(StoreActivity.this,Three.class);
//	                            startActivity(intent);
//	                        }else if (item%pictureList.size() == 3) {
//	                            Intent intent=new Intent(StoreActivity.this,Four.class);
//	                            startActivity(intent);
//	                        }
						}
						break ;
				}
				return false;
			}
		});
		startBannerTimer();

		//搜索框
		search_layout= (LinearLayout) home_page_view.findViewById(R.id.search_layout);
		search_layout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
				startActivity(intent);
			}
		});
		search_et= (EditText) home_page_view.findViewById(R.id.search_et);
		search_et.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
				startActivity(intent);
			}
		});

		//新品横向列表
		new_goods_horizontallistview=(HorizontalListView) home_page_view.findViewById(R.id.new_goods_horizontallistview);
		final int[] new_goods_images= {R.mipmap.goods,R.mipmap.brand,R.mipmap.brand,R.mipmap.goods,R.mipmap.goods,R.mipmap.brand};
		final String[] new_goods_names= {"纸尿裤0","纸尿裤1","纸尿裤2","纸尿裤3","纸尿裤4","纸尿裤5"};
		final String[] new_goods_prices= {"10.0","20.0","30.0","40.0","50.0","60.0"};
		newgoodshorizontallistviewAdapter=new NewGoodsHorizontalListViewAdapter(getApplicationContext(), new_goods_images, new_goods_names, new_goods_prices);
		new_goods_horizontallistview.setAdapter(newgoodshorizontallistviewAdapter);
		new_goods_horizontallistview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				newgoodshorizontallistviewAdapter.setSelectIndex(position);
				newgoodshorizontallistviewAdapter.notifyDataSetChanged();
				Intent intent=new Intent(getApplicationContext(),GoodsDetailActivity.class);
				startActivity(intent);
			}
		});

		//热门品牌横向列表
		hot_brand_horizontallistview=(HorizontalListView) home_page_view.findViewById(R.id.hot_brand_horizontallistview);
		final int[] hot_brand_images= {R.mipmap.goods,R.mipmap.goods,R.mipmap.goods,R.mipmap.brand,R.mipmap.brand};
		final String[] hot_brand_names= {"强生","好孩子","贝亲","康贝","贝因"};
		final String[] hot_brand_slogan= {"因爱而生","陪伴成长每一天","我们懂你","创造育儿的感动、协助育儿的伙伴","中国妈妈的安心选择"};
		hotbrandhorizontallistviewAdapter=new HotBrandHorizontalListViewAdapter(getApplicationContext(), hot_brand_images, hot_brand_names, hot_brand_slogan);
		hot_brand_horizontallistview.setAdapter(hotbrandhorizontallistviewAdapter);
		hot_brand_horizontallistview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				hotbrandhorizontallistviewAdapter.setSelectIndex(position);
				hotbrandhorizontallistviewAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), ""+hot_brand_names[position],
						Toast.LENGTH_SHORT).show();
			}
		});

		//家居日用横向列表
		household_daily_horizontallistview=(HorizontalListView) home_page_view.findViewById(R.id.household_daily_horizontallistview);
		final int[] household_daily_image= {R.mipmap.household_daily_itemimage,R.mipmap.household_daily_itemimage,R.mipmap.household_daily_itemimage,R.mipmap.household_daily_itemimage,R.mipmap.household_daily_itemimage,R.mipmap.household_daily_itemimage};
		final String[] household_daily_names= {"Serenade四件套玻璃杯0","Serenade四件套玻璃杯0","Serenade四件套玻璃杯1","Serenade四件套玻璃杯2","Serenade四件套玻璃杯3","Serenade四件套玻璃杯4","Serenade四件套玻璃杯5"};
		final String[] household_daily_price= {"99.0","100.0","100.0","89.6","100.0","120.0"};
		householdDailyHorizontalListViewAdapter=new HouseholdDailyHorizontalListViewAdapter(getApplicationContext(), household_daily_image, household_daily_names, household_daily_price);
		household_daily_horizontallistview.setAdapter(householdDailyHorizontalListViewAdapter);
		household_daily_horizontallistview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				householdDailyHorizontalListViewAdapter.setSelectIndex(position);
				householdDailyHorizontalListViewAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), ""+household_daily_names[position],
						Toast.LENGTH_SHORT).show();
			}
		});
		//童婴用品横向列表
		baby_product_horizontallistview=(HorizontalListView) home_page_view.findViewById(R.id.baby_product_horizontallistview);
		final int[] baby_product_images= {R.mipmap.baby_product_itemimage,R.mipmap.baby_product_itemimage,R.mipmap.baby_product_itemimage,R.mipmap.baby_product_itemimage,R.mipmap.baby_product_itemimage,R.mipmap.baby_product_itemimage};
		final String[] baby_product_names= {"婴儿马桶套装0","婴儿马桶套装1","英文马桶套装2","婴儿马桶套装3","婴儿马桶套装4","婴儿马桶套装5"};
		final String[] baby_product_prices= {"99.0","99.0","100.0","89.0","250.0","132.0"};
		babyProductHorizontalListViewAdapter=new BabyProductHorizontalListViewAdapter(getApplicationContext(), baby_product_images, baby_product_names, baby_product_prices);
		baby_product_horizontallistview.setAdapter(babyProductHorizontalListViewAdapter);
		baby_product_horizontallistview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				babyProductHorizontalListViewAdapter.setSelectIndex(position);
				babyProductHorizontalListViewAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), ""+baby_product_names[position],
						Toast.LENGTH_SHORT).show();
			}
		});

		//优惠商品列表
		discount_listview=(ListView) home_page_view.findViewById(R.id.discounts_list);
		discountListviewAdapter=new DiscountListviewAdapter(getApplicationContext(), discounts_ItemImage, discounts_ItemName, discounts_NewItemPrice, discounts_OldItemPrice);
		discount_listview.setAdapter(discountListviewAdapter);
		discount_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				discountListviewAdapter.setSelectIndex(position);
				discountListviewAdapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), ""+discounts_ItemName[position],
						Toast.LENGTH_SHORT).show();
			}
		});
		setListViewHeightBasedOnChildren(discount_listview);

		//商品类别GridView
		GridView goodsClassify_gridview=(GridView) home_page_view.findViewById(R.id.goods_classify_grid);
		int[] goodsClassify_Image=new int[] {R.mipmap.recommend,R.mipmap.baby_product,R.mipmap.pregnant_women_supplies,R.mipmap.costume,R.mipmap.digital_products,
				R.mipmap.food_beverage,R.mipmap.books,R.mipmap.household_daily,R.mipmap.electric_appliance,R.mipmap.guard_makeup};
		final String[] goodsClassify_Name=new String[] {"精选推荐","童婴用品","孕妇用品","服饰鞋靴","数码产品","食品饮料","书刊读物","家居日用","家用电器","个护化妆"};
		List<Map<String, Object>> goodsClassify_list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<goodsClassify_Image.length;i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("goodsClassify_image", goodsClassify_Image[i]);
			map.put("goodsClassify_name", goodsClassify_Name[i]);
			goodsClassify_list.add(map);
		}
		SimpleAdapter goodsClassify_Adapter=new SimpleAdapter(home_page_view.getContext(),goodsClassify_list,R.layout.goodsclassify_gridview_layout,new String[] {"goodsClassify_image","goodsClassify_name"},new int[] {R.id.category_Image,R.id.category_Name});
		goodsClassify_gridview.setAdapter(goodsClassify_Adapter);
		goodsClassify_gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), goodsClassify_Name[position], Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(getApplicationContext(),CategoryGoodsGridViewActivity.class);
				intent.putExtra("goodsClassify_name", goodsClassify_Name[position]);
				startActivity(intent);
			}
		});



		//家居日用主题，婴儿用品主题设置前景透明度
		RelativeLayout household_daily_theme_relativeLayout=(RelativeLayout) home_page_view.findViewById(R.id.household_daily_theme_relativeLayout);
		household_daily_theme_relativeLayout.getBackground().setAlpha(160);
		RelativeLayout baby_product_theme_relativeLayout= (RelativeLayout) home_page_view.findViewById(R.id.baby_product_theme_relativeLayout);
		baby_product_theme_relativeLayout.getBackground().setAlpha(160);
		//家居日用主题，婴儿用品主题图片设置
		//搜索框背景透明度
		EditText search_et=(EditText) home_page_view.findViewById(R.id.search_et);
		search_et.getBackground().setAlpha(180);


		/**
		 * 购物车部分
		 */
		ckAll = (CheckBox) shopping_cart_page_view.findViewById(R.id.select_all);
		showtotalPrice = (TextView) shopping_cart_page_view.findViewById(R.id.total_price);
		tvSettlement = (TextView) shopping_cart_page_view.findViewById(R.id.calculate_tv);
		list_shopping_cart = (ListView) shopping_cart_page_view.findViewById(R.id.shopping_cart_listview);
		ckAll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(shoppingCartItemList.size()!=0) {
					if(ckAll.isChecked()) {
						for(int i=0;i<shoppingCartItemList.size();i++) {
							shoppingCartItemList.get(i).setChoosed(true);
						}
						shoppingCartAdapter.notifyDataSetChanged();
					}else {
						for(int i=0;i<shoppingCartItemList.size();i++) {
							shoppingCartItemList.get(i).setChoosed(false);
						}
						shoppingCartAdapter.notifyDataSetChanged();
					}
				}
				statistics();
			}
		});
		tvSettlement.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lementOnder();
			}
		});
		initData();


		/**
		 * 分类页面
		 */

		classify_page_expandlistview=(ExpandableListView) classify_page_view.findViewById(R.id.classify_page_expendlist);
		classify_page_expandlistview.setAdapter(classifypageexpandablelistviewAdapter);
		//初始化数据
		initClassifyPageData();
		//为ExpandableListView设置Adapter
		classifypageexpandablelistviewAdapter=new ClassifyPageExpandableListViewAdapter(getApplicationContext(), classify_page_groupnameList, classify_page_itemnameList);
		classify_page_expandlistview.setAdapter(classifypageexpandablelistviewAdapter);
		//监听组点击
		classify_page_expandlistview.setOnGroupClickListener(new OnGroupClickListener() {
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				if(classify_page_groupnameList.get(groupPosition).isEmpty()) {
					return true;
				}
				return false;
			}
		});
		//监听每个分组里子控件的点击事件
		classify_page_expandlistview.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(getApplicationContext(),
						classifypageexpandablelistviewAdapter.getGroup(groupPosition) + ":"
								+  classifypageexpandablelistviewAdapter.getChild(groupPosition, childPosition) ,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});








		/**
		 * 我的部分
		 */
		head_portrait=(ImageView) mine_page_view.findViewById(R.id.head_portrait);
		roundBitmap();
		view_all_indents_tv=(TextView) mine_page_view.findViewById(R.id.view_all_indents);
		view_all_indents_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),AllIndentActivity.class);
				startActivity(intent);
			}
		});
		wait_for_payment_tv=(TextView) mine_page_view.findViewById(R.id.wait_for_payment);
		wait_for_payment_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),WaitForPaymentActivity.class);
				startActivity(intent);
			}
		});
		wait_for_sending_tv=(TextView) mine_page_view.findViewById(R.id.wait_for_sending);
		wait_for_sending_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),WaitForSendingActivity.class);
				startActivity(intent);
			}
		});
		wait_for_goods_tv=(TextView) mine_page_view.findViewById(R.id.wait_for_goods);
		wait_for_goods_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),WaitForGoodsActivity.class);
				startActivity(intent);
			}
		});
		wait_for_evaluate_tv=(TextView) mine_page_view.findViewById(R.id.wait_for_evaluate);
		wait_for_evaluate_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),WaitForEvaluateActivity.class);
				startActivity(intent);
			}
		});
		collect_tv=(TextView) mine_page_view.findViewById(R.id.collect);
		collect_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),CollectActivity.class);
				startActivity(intent);
			}
		});
		address_tv=(TextView) mine_page_view.findViewById(R.id.address);
		address_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),AddressActivity.class);
				startActivity(intent);
			}
		});
		suggestion_feedbaack_tv=(TextView) mine_page_view.findViewById(R.id.suggestion_feedback);
		suggestion_feedbaack_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),SuggestionFeedbackActivity.class);
				startActivity(intent);
			}
		});
		after_sales_service_tv=(TextView) mine_page_view.findViewById(R.id.after_sales_service);
		after_sales_service_tv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent=new Intent(getApplicationContext(),GoodsDetailActivity.class);
//				startActivity(intent);
			}
		});





		//适配器初始化并设置
		mPagerAdapter = new PagerAdapter() {
			public void destroyItem(ViewGroup container,int position,Object object) {
				container.removeView(mViews.get(position));
			}
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			@Override
			public int getCount() {
				return mViews.size();
			}
		};
		viewPager.setAdapter(mPagerAdapter);
	}


	private void initEvent() {
		home_page.setOnClickListener(this);
		classify_page.setOnClickListener(this);
		shopping_cart_page.setOnClickListener(this);
		mine_page.setOnClickListener(this);
//		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
//
//			@Override
//			public void onPageSelected(int arg0) {
//				int currentItem = viewPager.getCurrentItem();
//				switch (currentItem) {
//				case 0:
//					resetImg();
//					//滑到这个页面，图片按钮的图片会变成下面的图片
//					home_page_imagebutton.setImageResource(R.mipmap.home1);
//					break;
//				case 1:
//					resetImg();
//					classify_page_imagebutton.setImageResource(R.mipmap.classify1);
//					break;
//				case 2:
//					resetImg();
//					shopping_cart_page_imagebutton.setImageResource(R.mipmap.shopping_cart1);
//					break;
//				case 3:
//					resetImg();
//					mine_page_imagebutton.setImageResource(R.mipmap.mine1);
//					break;
//				default:
//					break;
//				}
//			}
//
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onPageScrollStateChanged(int arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
	}
	/**
	 * 判断哪个要显示，及设置按钮图片
	 */
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.home_page:
				viewPager.setCurrentItem(0);
				resetImg();
				home_page_imagebutton.setImageResource(R.mipmap.home1);
				break;
			case R.id.classify_page:
				viewPager.setCurrentItem(1);
				resetImg();
				classify_page_imagebutton.setImageResource(R.mipmap.classify1);
				break;
			case R.id.shopping_cart_page:
				viewPager.setCurrentItem(2);
				resetImg();
				shopping_cart_page_imagebutton.setImageResource(R.mipmap.shopping_cart1);
				break;
			case R.id.mine_page:
				viewPager.setCurrentItem(3);
				resetImg();
				mine_page_imagebutton.setImageResource(R.mipmap.mine1);
				break;
			default:
				break;
		}
	}
	/**
	 * 把所有图片变暗
	 */
	private void resetImg() {
		home_page_imagebutton.setImageResource(R.mipmap.home0);
		classify_page_imagebutton.setImageResource(R.mipmap.classify0);
		shopping_cart_page_imagebutton.setImageResource(R.mipmap.shopping_cart0);
		mine_page_imagebutton.setImageResource(R.mipmap.mine0);
	}



	/**
	 * 主页部分
	 */
	/**
	 * 开始轮播
	 */
	private void startBannerTimer() {
		if (timer == null) {
			timer = new Timer();
		}
		if (mBannerTimerTask != null) {
			mBannerTimerTask.cancel();
		}
		mBannerTimerTask = new BannerTimerTask(bannerHandler);
		if (timer != null && mBannerTimerTask != null) {
			// 循环3秒执行
			timer.schedule(mBannerTimerTask, 3000, 3000);
		}
	}


	/**
	 * 销毁时,关闭任务,防止异常
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (null != mBannerTimerTask) {
			mBannerTimerTask.cancel();
			mBannerTimerTask = null;
		}
	}
	/*
     * 头像
     */
	private void roundBitmap(){
		//如果是圆的时候，我们应该把bitmap图片进行剪切成正方形， 然后再设置圆角半径为正方形边长的一半即可
		Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.beauty);
		Bitmap bitmap = null;
		//将长方形图片裁剪成正方形图片
		if (image.getWidth() == image.getHeight()) {
			bitmap = Bitmap.createBitmap(image, image.getWidth() / 2 - image.getHeight() / 2, 0, image.getHeight(), image.getHeight());
		}
		else {
			bitmap = Bitmap.createBitmap(image, 0, image.getHeight() / 2 - image.getWidth() / 2, image.getWidth(), image.getWidth());
		}
		RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
		//圆角半径为正方形边长的一半
		roundedBitmapDrawable.setCornerRadius(bitmap.getWidth() / 2);
		//抗锯齿
		roundedBitmapDrawable.setAntiAlias(true);
		head_portrait.setImageDrawable(roundedBitmapDrawable);
	}

	/**
	 * 购物车部分 初始化数据
	 */
	protected void initData() {
		for(int i=0;i<shopping_cart_ItemImage.length;i++) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setItemimage(shopping_cart_ItemImage[i]);
			shoppingCartItem.setItemname(shopping_cart_ItemName[i]);
			shoppingCartItem.setItemattr(shopping_cart_ItemAttr[i]);
			shoppingCartItem.setItemprice(shopping_cart_ItemPrice[i]);
			shoppingCartItem.setItemamount(shopping_cart_ItemAmount[i]);
			shoppingCartItemList.add(shoppingCartItem);
		}
		shoppingCartAdapter = new ShoppingCartAdapter(getApplicationContext());
		shoppingCartAdapter.setCheckInterface(this);
		shoppingCartAdapter.setModifyCountInterface(this);
		list_shopping_cart.setAdapter(shoppingCartAdapter);
		shoppingCartAdapter.setShoppingCartItemList(shoppingCartItemList);
	}
	//结算订单、支付
	private void lementOnder() {
		//选中的需要提交的商品清单
		for(ShoppingCartItem item:shoppingCartItemList) {
			boolean choosed = item.isChoosed();
			if(choosed) {
				int itemid=item.getId();
				int itemimage=item.getItemimage();
				String itemname=item.getItemname();
				String itemattr=item.getItemattr();
				double itemprice=item.getItemprice();
				int itemamount=item.getItemamount();
				Log.d(TAG, itemid+"---id---"+itemname+"---"+itemattr+"---"+itemprice+"---"+itemamount);
			}
		}
		ToastUtil.showL(getApplicationContext(), "总价："+totalPrice);
	}
	//单选   position组元素位置 isChecked组元素选中与否
	public void checkGroup(int position,boolean isChecked) {
		shoppingCartItemList.get(position).setChoosed(isChecked);
		if(isAllCheck()) {
			ckAll.setChecked(true);
		}else {
			ckAll.setChecked(false);
		}
		shoppingCartAdapter.notifyDataSetChanged();
		statistics();
	}
	//遍历list集合
	private boolean isAllCheck() {
		for(ShoppingCartItem group:shoppingCartItemList) {
			if(!group.isChoosed()) {
				return false;
			}
		}
		return true;
	}
	//统计操作  1、先清空全局计数器2、遍历所有子元素，只要是被选中状态的，就进行相关的计算操作3、给底部的textView进行数据填充
	public void statistics() {
		totalCount=0;
		totalPrice=0.00;
		for(int i=0;i<shoppingCartItemList.size();i++) {
			ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(i);
			if(shoppingCartItem.isChoosed()) {
				totalCount++;
				totalPrice+=shoppingCartItem.getItemprice()*shoppingCartItem.getItemamount();
			}
		}
		totalPrice=(double)Math.round(totalPrice*100)/100;
		showtotalPrice.setText(totalPrice+"");
		tvSettlement.setText("结算("+totalCount+")");
	}
	//增加  position组元素位置 showCountView用于展示变化后数量的View  isChecked子元素选中与否
	public void doIncrease(int position,View showCountView,boolean isChecked) {
		ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(position);
		int currentCount = shoppingCartItem.getItemamount();
		currentCount++;
		shoppingCartItem.setItemamount(currentCount);
		((TextView) showCountView).setText(currentCount+"");
		shoppingCartAdapter.notifyDataSetChanged();
		statistics();
	}
	//删减  position组元素位置    showCountView用于展示变化后数量的view   isChecked子元素选中与否
	public void doDecrease(int position,View showCountView,boolean isChecked) {
		ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(position);
		int currentCount = shoppingCartItem.getItemamount();
		if(currentCount==1) {
			return;
		}
		currentCount--;
		shoppingCartItem.setItemamount(currentCount);
		((TextView) showCountView).setText(currentCount+"");
		shoppingCartAdapter.notifyDataSetChanged();
		statistics();
	}
	//删除
	public void childDelete(int position) {
		shoppingCartItemList.remove(position);
		shoppingCartAdapter.notifyDataSetChanged();
		statistics();
	}
	//由于listview在scrollview中只能显示一项，所以要动态计算listview的height进行动态设置
	public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
	//分类页面初始化数据
	private void initClassifyPageData() {
		//组名
		classify_page_groupnameList=new ArrayList<String>();
		String[] classify_page_groupname=new String[] {"精选推荐","童婴用品","孕妈用品","服饰鞋靴","数码产品","家居日用","家用电器","食品饮料","书刊读物","个护化妆"};
		for(int i=0;i<classify_page_groupname.length;i++) {
			classify_page_groupnameList.add(classify_page_groupname[i]);
		}
		classify_page_itemnameList=new ArrayList<List<String>>();

		//精选推荐组
		List<String> classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_first=new String[] {"百元好物","本季热销"};
		for(int i=0;i<classify_page_first.length;i++) {
			classify_page_itemlist.add(classify_page_first[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//童婴用品组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_second=new String[] {"喂养用品","清洁/洗护","奶粉","尿裤","辅食","营养品"};
		for(int i=0;i<classify_page_second.length;i++) {
			classify_page_itemlist.add(classify_page_second[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//孕妈用品组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_third=new String[] {"洗护","孕妈营养","孕妇服饰","产后塑身"};
		for(int i=0;i<classify_page_third.length;i++) {
			classify_page_itemlist.add(classify_page_third[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//服饰鞋靴组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_fourth=new String[] {"男装","女装","童婴","袜子","内衣裤"};
		for(int i=0;i<classify_page_fourth.length;i++) {
			classify_page_itemlist.add(classify_page_fourth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//数码产品组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_fifth=new String[] {"电子教育","影音娱乐","智能设备","数码配件","手机","电脑"};
		for(int i=0;i<classify_page_fifth.length;i++) {
			classify_page_itemlist.add(classify_page_fifth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//家居日用组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_sixth=new String[] {"床上用品","家具","餐厨用具","装饰摆件","日常用品"};
		for(int i=0;i<classify_page_sixth.length;i++) {
			classify_page_itemlist.add(classify_page_sixth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//家用电器组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_seventh=new String[] {"厨房小店","生活电器","个人健康","五金家装"};
		for(int i=0;i<classify_page_seventh.length;i++) {
			classify_page_itemlist.add(classify_page_seventh[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//食品饮料组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_eighth=new String[] {"进口食品","休闲食品","地方特产","饮料冲调"};
		for(int i=0;i<classify_page_eighth.length;i++) {
			classify_page_itemlist.add(classify_page_eighth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//书刊读物组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_ninth=new String[] {"文艺","少儿","生活","科技","教育"};
		for(int i=0;i<classify_page_ninth.length;i++) {
			classify_page_itemlist.add(classify_page_ninth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);

		//书刊读物组
		classify_page_itemlist=new ArrayList<String>();
		String[] classify_page_tenth=new String[] {"面部护肤","口腔护理","洗发护发","香水彩妆"};
		for(int i=0;i<classify_page_tenth.length;i++) {
			classify_page_itemlist.add(classify_page_tenth[i]);
		}
		classify_page_itemnameList.add(classify_page_itemlist);
	}
}