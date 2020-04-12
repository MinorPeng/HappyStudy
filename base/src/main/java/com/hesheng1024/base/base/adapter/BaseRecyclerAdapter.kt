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

    private val mDatas: MutableList<T> = ArrayList()
    protected var mListener: IBaseIClickListener? = null

    init {
        if (!datas.isNullOrEmpty()) {
            this.addDatas(datas)
        }
    }

    open fun add(data: T) {
        mDatas.add(data)
        notifyDataSetChanged()
    }

    open fun add(position: Int, data: T) {
        mDatas.add(position, data)
        notifyDataSetChanged()
    }

    open fun addDatas(datas: List<T>) {
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        mDatas.removeAt(position)
        notifyDataSetChanged()
    }

    open fun remove(data: T) {
        mDatas.remove(data)
        notifyDataSetChanged()
    }

    open fun clear() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    open fun getData(position: Int): T {
        return mDatas[position]
    }

    open fun getDatas(): List<T> {
        return mDatas
    }

    open fun setClickListener(listener: IBaseIClickListener) {
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false))
    }

    override fun getItemCount(): Int {
        return if (mDatas.isNullOrEmpty()) 0 else mDatas.size
    }

    /**
     * item layout_block_edit_text id
     * @return resId
     */
    protected abstract fun getItemLayoutId(): Int
}