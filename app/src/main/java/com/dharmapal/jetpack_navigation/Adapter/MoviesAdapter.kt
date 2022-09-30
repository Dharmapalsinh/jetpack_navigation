

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dharmapal.jetpack_navigation.Models.VideoResult
import com.dharmapal.jetpack_navigation.R
import com.dharmapal.jetpack_navigation.databinding.MovieRowBinding


class MoviesAdapter(val onClick:(VideoResult)->Unit,val onwatchclick:(VideoResult)->Unit): RecyclerView.Adapter<myViewHolder>() {

    var data:List<VideoResult> = emptyList()
    var mypopupWindow:PopupWindow= PopupWindow()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= MovieRowBinding.inflate(inflater,parent,false)
        return myViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val b1:VideoResult=data[position]
        Glide.with(holder.itemView.context).load(b1.thumbnail!!.static).into(holder.binding.ivUserImage)
        holder.binding.tvTitle.text=b1.title
        holder.itemView.setOnClickListener {
            val  inflater:LayoutInflater = holder.itemView.context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.custom_popup_menu, null)

            val watch=view.findViewById<TextView>(R.id.tv_watch)
            val viewdetails=view.findViewById<TextView>(R.id.tv_detail)

            mypopupWindow =  PopupWindow(view,500, 330, true)
            mypopupWindow.showAsDropDown(holder.binding.ivUserImage)

            watch.setOnClickListener {
                onwatchclick(b1)
            }

            viewdetails.setOnClickListener {
                onClick(b1)
                mypopupWindow.dismiss()
            }
//            onClick(b1)
//            val wrapper: Context = ContextThemeWrapper(holder.itemView.context, R.style.popupMenuStyle)
//            val popup = PopupMenu(wrapper,holder.binding.tvTitle)
//
//            popup.inflate(R.menu.menufilecontext)
//            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
//                override fun onMenuItemClick(item: MenuItem): Boolean {
//                    when (item.getItemId()) {
//                        R.id.Watch ->{
//                            onwatchclick(b1)
//                            return true
//                        }
//                        R.id.View->{
//                            onClick(b1)
//                            return true
//                        }
//                        else -> return false
//                    }
//                }
//            })
//            popup.setForceShowIcon(true)
//            popup.show()

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }



}


class myViewHolder(val binding: MovieRowBinding): RecyclerView.ViewHolder(binding.root)



