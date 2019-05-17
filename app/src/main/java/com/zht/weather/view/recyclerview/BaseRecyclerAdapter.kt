package com.zht.weather.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *   author  :zhangtao
 *   date    :2019/5/13 16:21
 *   desc    :
 */
abstract class BaseRecyclerAdapter<T>(var mContext:Context,var mData:ArrayList<T>,private var layoutId:Int)
                                        : RecyclerView.Adapter<ViewHolder>() {

    private var mInflater : LayoutInflater? = null
    private var mTypeSupport:MultiType<T>? = null

    private var mItemClickListener: OnItemClickListener? = null

    private var mItemLongClickListener:OnItemLongClickListener? = null

    protected var datas:ArrayList<T>? = null

    init{
        mInflater = LayoutInflater.from(mContext)
        datas = mData
    }

    constructor(context: Context,data : ArrayList<T> ,typeSupport:MultiType<T>):this(context,data,-1){
        mTypeSupport = typeSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(mTypeSupport != null){
            layoutId = viewType
        }
        val mView = mInflater?.inflate(layoutId,parent,false)
        return ViewHolder(mView!!)
    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val obj:T = mData[position]

        bindData(holder,obj,position)

        mItemClickListener?.let { holder.itemView.setOnClickListener{
            mItemClickListener!!.onItemClick(obj,position)} }

        mItemLongClickListener?.let { holder.itemView.setOnLongClickListener{
            mItemLongClickListener!!.onItemLongClick(obj,position)
        } }
    }

    protected abstract fun bindData(holder: ViewHolder, data: T, position: Int)

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }
}