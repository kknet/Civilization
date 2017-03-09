package com.esotericsoftware.spine;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState.TrackEntry;

public class MyAnimationHook {
	Map<String, Array<MyUevent>> maps = new HashMap<String, Array<MyUevent>>(4);
	
	public void register(MyUevent e){
		Array<MyUevent> a = maps.get(e.animation);
		if(a==null) a = new Array<MyUevent>();
		a.add(e);
		a.sort();
		maps.put(e.animation, a);
	}
	
	protected static int b_min(Array<MyUevent> data, float key) {
		int len = data.size;
		if (data.get(len - 1).percent <= key)
			return -1;
		int l = 0, r = len - 1;
		while (l <= r) {
			int m = (r + l) / 2;
			if (data.get(m).percent <= key) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return l;
	}
	public void trigger(String animation, TrackEntry current, int i){
		Array<MyUevent> a = maps.get(animation);
		
		if(a!=null && a.size>0){
			float last = current.lastTime / current.endTime; 
			float time = current.time / current.endTime; 
			if(last>0){
				last -= Math.floor(last);
			}
			time -= Math.floor(time);
			float key = last > time ? -1f : last;
			int ii = b_min(a, key);
			if(ii<0) return;
			MyUevent e;
			while(ii<a.size){
				e = a.get(ii);
				if(e.percent > time)
					break;
				if(e.invoke(i, e.userData)){
					a.removeIndex(ii);
				}else{
					ii++;
				}
			}
		}
	}
}
