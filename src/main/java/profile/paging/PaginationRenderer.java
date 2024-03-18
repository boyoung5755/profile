package profile.paging;

import profile.vo.PaginationInfo;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 18.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 18. boyoung : 최초작성
 * </PRE>
 */
public interface PaginationRenderer {
	/**
	 * PaginationInfo 의 프로퍼티(startPage~endPage)에 따라 페이지 링크를 생성.
	 * @param paging
	 * @return
	 */
	public String renderPagination(PaginationInfo<?> paging);
}
