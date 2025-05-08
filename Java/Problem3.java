public int lengthOfLongestSubstring(String s) {
    int maxLen = 0; // Guarda el resultado final
    int left = 0; // Inicio de la ventana
    HashSet<Character> set = new HashSet<>(); // Guarda los caracteres actuales en la ventana

    for (int right = 0; right < s.length(); right++) {
        // Mientras haya un carácter repetido, movemos el inicio de la ventana
        while (set.contains(s.charAt(right))) {
            set.remove(s.charAt(left)); // Sacamos el carácter del lado izquierdo
            left++; // Avanzamos el inicio de la ventana
        }

        // Agregamos el nuevo carácter
        set.add(s.charAt(right));

        // Actualizamos la longitud máxima si esta ventana es más larga
        maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
}
