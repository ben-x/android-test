package ng.riby.androidtest


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import ng.riby.androidtest.database.Distance
import java.util.ArrayList

class DistanceAdapter(private val context: Context, private var distanceViewModel: DistanceViewModel?)
    : RecyclerView.Adapter<DistanceAdapter.DistanceHolder>() {
    private var distances: List<Distance> = ArrayList()

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, position: Int): DistanceHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.distance_item, viewGroup, false)
        return DistanceHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull distanceHolder: DistanceHolder, position: Int) {
        val currentDistance = distances[position]
        distanceHolder.startLat.text = currentDistance.startLat
        distanceHolder.startLon.text = currentDistance.startLon
        distanceHolder.endLat.text = currentDistance.endLat
        distanceHolder.endLon.text = currentDistance.endLon

    }


    fun setDistances(distances: List<Distance>) {
        this.distances = distances
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return distances.size
    }

    fun getDistanceAt(position: Int): Int {
        return distances[position].distanceId.toInt()
    }

    inner class DistanceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val startLat: TextView = itemView.findViewById(R.id.startLat)
        val startLon: TextView = itemView.findViewById(R.id.startLon)
        val endLat: TextView = itemView.findViewById(R.id.endLat)
        val endLon: TextView = itemView.findViewById(R.id.endLon)
    }
}
