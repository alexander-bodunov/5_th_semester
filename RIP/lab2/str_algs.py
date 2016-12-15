def reverseString(stringReverseOld):
    stringReverseNew = ''
    for n in reversed(range(len(stringReverseOld))):
        stringReverseNew = stringReverseNew+stringReverseOld[n]
    return stringReverseNew

print(reverseString('Sasha'))
print(reverseString('A'))
print(reverseString(''))
print(reverseString('   a   m   '))

