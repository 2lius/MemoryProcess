package com.lius.MemoryProcess;

import com.lius.MemoryProcess.stdLibrary.MemoryOperation;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.*;

import java.util.Objects;

/**
 * 内存进程操作类
 * @author lius
 * @date 2025/02/21
 */
@SuppressWarnings("unused")
public class MemoryProcess {

    User32 user32 = User32.INSTANCE;
    Kernel32 kernel32 = Kernel32.INSTANCE;
    private String windowName;
    private WinDef.HWND windowHwnd;
    private IntByReference pid;
    private WinNT.HANDLE processHandle;

    /**
     * 构造进程函数
     * @param windowName 窗口名称
     */
    public MemoryProcess(String windowName)
    {
        this.windowName = windowName;
        this.windowHwnd = user32.FindWindow(null, this.windowName);
        if (user32.IsWindow(windowHwnd)){
            this.pid = new IntByReference();
            user32.GetWindowThreadProcessId(windowHwnd, pid);
        }
    }
    /**
     * 构造进程函数
     * @param pidNum 进程id
     */
    public MemoryProcess(int pidNum)
    {
        this.pid = new IntByReference(pidNum);
    }

    /**
     * 获取进程id
     * @return 进程id
     */
    public int getProcessId(){
        return Objects.nonNull(pid)?pid.getValue():-1;
    }

    /**
     * 打开进程
     * @return 打开进程结果
     */
    public boolean openProcess(){
        if(pid.getValue()>=0){
            processHandle = kernel32.OpenProcess(Kernel32.PROCESS_ALL_ACCESS,false,pid.getValue());
            return !processHandle.equals(WinNT.INVALID_HANDLE_VALUE);
        }
        return false;
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public byte readByte(int baseAddress, int... offsets)
    {
        ByteByReference resultBuffer = new ByteByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public short readShort(int baseAddress, int... offsets)
    {
        ShortByReference resultBuffer = new ShortByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public int readInt(int baseAddress, int... offsets)
    {
        IntByReference resultBuffer = new IntByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public long readLong(int baseAddress, int... offsets)
    {
        LongByReference resultBuffer = new LongByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public float readFloat(int baseAddress, int... offsets)
    {
        FloatByReference resultBuffer = new FloatByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 读取数据
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 读取数据
     */
    public double readDouble(int baseAddress, int... offsets)
    {
        DoubleByReference resultBuffer = new DoubleByReference();
        MemoryOperation.readData(processHandle,resultBuffer, baseAddress, offsets);
        return resultBuffer.getValue();
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeByte(byte writeVal,int baseAddress, int... offsets)
    {
        ByteByReference resultBuffer = new ByteByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeShort(short writeVal,int baseAddress, int... offsets)
    {
        ShortByReference resultBuffer = new ShortByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeInt(int writeVal,int baseAddress, int... offsets)
    {
        IntByReference resultBuffer = new IntByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeLong(long writeVal,int baseAddress, int... offsets)
    {
        LongByReference resultBuffer = new LongByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeFloat(float writeVal,int baseAddress, int... offsets)
    {
        FloatByReference resultBuffer = new FloatByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 写入数据
     * @param writeVal 写入值
     * @param baseAddress 基地址
     * @param offsets 偏移量
     * @return 写入结果
     */
    public boolean writeDouble(double writeVal,int baseAddress, int... offsets)
    {
        DoubleByReference resultBuffer = new DoubleByReference(writeVal);
        return MemoryOperation.writeData(processHandle,resultBuffer, baseAddress, offsets);
    }

    /**
     * 关闭进程
     */
    public void closeProcess(){
        if(Objects.nonNull(processHandle)){
            kernel32.CloseHandle(processHandle);
        }
    }
}
