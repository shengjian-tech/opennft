package net.shengjian.frame.util;

public class CommonEnum {

    /**
     * 伪删除状态
     *
     * @author cxy
     */
    public enum ACTIVE {
        未删除(1), 已删除(0);
        private Integer state;

        private ACTIVE(Integer state) {
            this.state = state;
        }

        public Integer getState() {
            return state;
        }

    }
}
