package com.lius.MemoryProcess.stdLibrary;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.IntByReference;

public class MemoryOperation {

    private final static Kernel32 kernel32 = Kernel32.INSTANCE;
    public static void readData(WinNT.HANDLE processHandle, long address, ByReference buffer)
    {
        Pointer lpBaseAddress = new Pointer(address);
        long nSize = ((Memory)buffer.getPointer()).size();
        IntByReference lpNumberOfBytesRead = new IntByReference();
        lpNumberOfBytesRead.setValue(0);
        kernel32.ReadProcessMemory(processHandle, lpBaseAddress, buffer.getPointer(), (int) nSize, lpNumberOfBytesRead);
    }

    public static boolean writeData(WinNT.HANDLE processHandle, long address, ByReference buffer)
    {
        Pointer lpBaseAddress = new Pointer(address);
        long nSize = ((Memory)buffer.getPointer()).size();
        IntByReference lpNumberOfBytesRead = new IntByReference();
        lpNumberOfBytesRead.setValue(0);
        return kernel32.WriteProcessMemory(processHandle, lpBaseAddress, buffer.getPointer(), (int) nSize, lpNumberOfBytesRead);
    }

    public static void readData(WinNT.HANDLE processHandleNum, ByReference resultBuffer, int baseAddress, int... offsets)
    {
        IntByReference buffer = new IntByReference();
        readData(processHandleNum,baseAddress,buffer);
        for (int i = 0; i < offsets.length; i++) {
            buffer.setValue(buffer.getValue()+offsets[i]);
            if(i<offsets.length-1){
                readData(processHandleNum,buffer.getValue(),buffer);
            }else{
                readData(processHandleNum, buffer.getValue(),resultBuffer);
            }
        }
    }

    public static boolean writeData(WinNT.HANDLE processHandleNum, ByReference writeBuffer, int baseAddress, int... offsets)
    {
        IntByReference buffer = new IntByReference();
        readData(processHandleNum,baseAddress,buffer);
        for (int i = 0; i < offsets.length; i++) {
            buffer.setValue(buffer.getValue()+offsets[i]);
            if(i<offsets.length-1){
                readData(processHandleNum,buffer.getValue(),buffer);
            }else{
                return writeData(processHandleNum, buffer.getValue(),writeBuffer);
            }
        }
        return false;
    }

}