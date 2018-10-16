AIO
=======
#####Alex's IO
-----
###How to Use
*Command Line
    *Call AIO.user.<method>
    *All read methods halt execution (of the thread) until the input has been entered
    *Method variants
        *Each type has variants of its read method with different limits, or none
        *For example, readString type methods have varients with limited lengths and readInt type methods have varients with limited ranges
*Files
    *Call AIO.file.<method>
    *Each read/write method will accept either a provided file path or whatever has been set