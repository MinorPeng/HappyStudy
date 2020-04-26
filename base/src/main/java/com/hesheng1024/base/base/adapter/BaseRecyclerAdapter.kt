package com.hesheng1024.base.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author hesheng1024
 * @date 2020/2/8 11:28
 */
abstract class BaseRecyclerAdapter<T>(datas: List<T>? = null) : RecyclerView.Adapter<BaseViewHolder>() {

    private val mData: MutableList<T> = ArrayList()
    protected var mListener: IBaseIClickListener? = null

    init {
        if (!datas.isNullOrEmpty()) {
            this.addList(datas)
        }
    }

    open fun setList(data: List<T>) {
        mData.clear()
        addList(data)
    }

    open fun add(data: T) {
        mData.add(data)
        notifyDataSetChanged()
    }

    open fun add(position: Int, data: T) {
        mData.add(position, data)
        notifyDataSetChanged()
    }

    open fun addList(data: List<T>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        mData.removeAt(position)
        notifyDataSetChanged()
    }

    open fun remove(data: T) {
        mData.remove(data)
        notifyDataSetChanged()
    }

    open fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    open fun get(position: Int): T {
        return mData[position]
    }

    open fun getList(): List<T> {
        return mData
    }

    open fun setClickListener(listener: IBaseIClickListener) {
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false))
    }

    override fun getItemCount(): Int {
        return if (mData.isNullOrEmpty()) 0 else mData.size
    }

    /**
     * item layout_block_edit_text id
     * @return resId
     */
    protected abstract fun getItemLayoutId(): Int
}