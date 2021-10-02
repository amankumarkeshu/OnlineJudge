
def add(a, b):
    res = 0
    # CRIO_SOLUTION_START_MODULE_L1_PROBLEMS
    res = a + b
    # CRIO_SOLUTION_END_MODULE_L1_PROBLEMS

    return res

if __name__ == '__main__':

    inp = input().split()

    a = int(inp[0])

    b = int(inp[1])

    print(add(a,b))
