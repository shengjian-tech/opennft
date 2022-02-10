package net.shengjian.makerone.exception;

/**
 * @descriptions: 异常
 * @author: YSK
 * @date: 2021/12/24 9:37
 * @version: 1.0
 */
public class NFTException extends RuntimeException {
    public NFTException(String message) {
        super(message);
    }
    public static NFTException NFTExceptionERC1155(Exception e) {
        final String message = e.getMessage();
        if (message.contains("ERC1155: token protect time is unexpired")) {
            return new NFTException("该商品处于交易保护期!");
        } else if (message.contains("ERC1155: caller is not owner nor approved")) {
            return new NFTException("没有权限!");
        } else if (message.contains("ERC1155: transfer to the zero address")) {
            return new NFTException("不能交易向不存在的地址!");
        } else if (message.contains("ERC1155: insufficient balance for transfer")) {
            return new NFTException("商品余额不足");
        } else if (message.contains("ERC1155: token id is exist")) {
            return new NFTException("token id 已存在");
        }else if (message.contains("NOT_ENOUGH_UTXO_ERROR")) {
            return new NFTException("没有足够的gas完成交易");
        }
        return new NFTException("合约未知异常!");
    }
}
