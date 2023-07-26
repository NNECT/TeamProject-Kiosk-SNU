package com.KioskSNU.common;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class Scaler {
    /**
     * 값의 합이 targetScaleSum과 같도록 스케일링하여 String-Integer 형태의 Map으로 반환한다.
     * @param dataMap key: String, value: Integer
     * @param targetScaleSum 스케일링 후의 값의 합
     * @return 스케일링된 값의 합이 targetScaleSum과 같도록 스케일링된 String-Integer 형태의 LinkedHashMap
     */
    public Map<String, Integer> sumScaler(Map<String, Integer> dataMap, int targetScaleSum) {
		List<String> keyList = new ArrayList<>(dataMap.size());
        List<Integer> valueList = new ArrayList<>(dataMap.size());

        // dataMap의 key와 value를 각각 keyList와 valueList에 저장한다.
        int[] sum = {0};
        dataMap.forEach((key, value) -> {
            keyList.add(key);
            valueList.add(value);
            sum[0] += value;
        });

        // valueList의 원소들을 targetScaleSum에 맞게 스케일링한다.
        int newSum = 0;
        for (int i = 0; i < valueList.size(); i++) {
            double ratio = (double) valueList.get(i) / sum[0];
            int newCount = (int) Math.floor(ratio * targetScaleSum);
            valueList.set(i, newCount);
            newSum += newCount;
        }

        // 스케일링 후의 값의 합이 targetScaleSum과 같도록 valueList의 원소들을 조정한다.
        int i = valueList.size() - 1;
        while (newSum < targetScaleSum && i >= 0) {
            valueList.set(i, valueList.get(i) + 1);
            newSum++;
            i--;
        }

        // String-Integer 형태의 LinkedHashMap을 반환한다.
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        for (int j = 0; j < keyList.size(); j++) {
            resultMap.put(keyList.get(j), valueList.get(j));
        }
        return resultMap;
    }

    /**
     * dataMap의 key String에 따른 각각의 value List에 들어있는 원소의 개수가 사용되는 정보이다.
     * 값의 합이 targetScaleSum과 같도록 스케일링하여 String-Integer 형태의 Map으로 반환한다.
     * @param listDataMap key: String, value: ? (List<?>)
     * @param targetScaleSum 스케일링 후의 값의 합
     * @return 스케일링된 값의 합이 targetScaleSum과 같도록 스케일링된 String-Integer 형태의 LinkedHashMap
     */
    public Map<String, Integer> sumMapScaler(Map<String, ?> listDataMap, int targetScaleSum) {
        Map<String, Integer> dataMap = new LinkedHashMap<>();
        listDataMap.forEach((key, value) -> dataMap.put(key, ((List<?>) value).size()));
        return sumScaler(dataMap, targetScaleSum);
    }
}
