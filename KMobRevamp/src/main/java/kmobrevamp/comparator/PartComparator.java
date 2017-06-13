package kmobrevamp.comparator;

import java.util.Comparator;

import kmobrevamp.model.Part;

public class PartComparator implements Comparator<Part> {

	@Override
	public int compare(Part p1, Part p2) {
		if(p1.getRate()>p2.getRate())
		{
			return 1;
			
		}
		else if(p1.getRate()<p2.getRate())
		{
			return -1;
		}
		else{
		return 0;
		}
	}

}
