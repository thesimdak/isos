package cz.svetsplhu.isos.repository.model.comparator;


import cz.svetsplhu.isos.repository.model.ParticipationEntity;

import java.util.Comparator;

/**
 * Comparator ordering climber's participation by time from fastest time to slowest one.
 */
public class ParticipationComparator implements Comparator<ParticipationEntity> {

    @Override
    public int compare(ParticipationEntity o1, ParticipationEntity o2) {
        int index = 1;
        return compare(o1, o2, index);
    }

    private int compare(ParticipationEntity o1, ParticipationEntity o2, int index) {
        if (o1.getTimeDoubleList().size()>=index && o2.getTimeDoubleList().size()>=index) {
            int comparison = o1.getTimeDoubleList().get(index-1).compareTo(o2.getTimeDoubleList().get(index-1));
            if (comparison == 0) {
                return compare(o1, o2, index+1);
            } else {
                return comparison;
            }
        } else if (o1.getTimeDoubleList().size() >= index) {
            return 1;
        }  else if (o2.getTimeDoubleList().size() >= index) {
            return -1;
        }
        return 0;
    }

}
