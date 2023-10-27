package org.anagram;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

public class AnagramService {
    //todo: extract Map<Integer, Integer> and Set<String> in classes for better readability and loose coupling
    private final Map<Map<Integer, Integer>, Set<String>> cache = new HashMap<>();

    public boolean isAnagrams(String text1, String text2) {
        checkNotNull(text1);
        checkNotNull(text2);

        Map<Integer, Integer> text1AnagramId = getAnagramIdentity(text1);
        Map<Integer, Integer> text2AnagramId = getAnagramIdentity(text2);

        boolean isAnagram = text1AnagramId.equals(text2AnagramId);

        if (isAnagram) {
            cache.computeIfAbsent(text1AnagramId, key -> new HashSet<>());
            cache.get(text1AnagramId).addAll(newArrayList(text1, text2));
        }

        return isAnagram;
    }

    public Set<String> getAllKnownAnagrams(String text) {
        checkNotNull(text);

        Map<Integer, Integer> anagramIdentity = getAnagramIdentity(text);

        Set<String> anagrams = Sets.newHashSet(cache.getOrDefault(anagramIdentity, Set.of()));
        anagrams.remove(text);

        return anagrams;
    }

    private static String removeDelimiters(String text2) {
        return text2.replaceAll(" ", "");//todo: add support for other delimiters
    }

    private static Map<Integer, Integer> getAnagramIdentity(String text) {
        Map<Integer, Integer> anagramIdentity = new HashMap<>();

        removeDelimiters(text).chars()
                .forEach(aChar -> anagramIdentity.compute(aChar, (charKey, charOccurrence) -> charOccurrence == null ? 1 : charOccurrence + 1));

        return anagramIdentity;
    }
}
