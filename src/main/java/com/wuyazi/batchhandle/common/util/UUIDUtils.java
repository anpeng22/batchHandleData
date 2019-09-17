package com.wuyazi.batchhandle.common.util;

import java.util.UUID;

/**
 * ClassName: UUIDUtils <br/>
 * Description: UUID工具类. <br/>
 * Date: 2016年3月25日 上午10:18:03 <br/>
 *
 * @author an
 * @version 1.0
 * @since 1.7
 */
public class UUIDUtils {

	/**
	 * 获取32位UUID
	 * @return
	 */
	public static String get32UUID() {
		return get36UUID().replace("-", "");
	}
	
	/**
	 * 获取36位UUID
	 * @return
	 */
	public static String get36UUID() {
		return UUID.randomUUID().toString();
	}
}
