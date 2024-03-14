package profile.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 8.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 8. boyoung : 최초작성
 * </PRE>
 */

@Data
@EqualsAndHashCode(of="imgNo")
public class GalleryVO implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private int imgNo;
	private LocalDateTime imgRdate;
	private String imgDel;
	
	private MultipartFile[] profileFile;
	
	private String fileCode  ; 
	
	
	private String gallImage;   //ck에터로 저장한 이미지
	
	//1:N관계형성
	private List<FileVO> fileList;
	
	
	public void setProfileFile(MultipartFile[] profileFile) {
		if(profileFile != null) {
			this.profileFile = Arrays.stream(profileFile)
								. filter((f)->!f.isEmpty())
								.toArray(MultipartFile[]::new);
		}
		if(this.profileFile!=null) {
			this.fileList = Arrays.stream(this.profileFile)
								.map((f)->new FileVO(f))
								.collect(Collectors.toList());
		}
		
	}
}
