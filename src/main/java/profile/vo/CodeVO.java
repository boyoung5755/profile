package profile.vo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString
@Data
@EqualsAndHashCode(of="codeNo")
public class CodeVO {
	
	private int codeNo;
	private int stackNo;
	private String codeContent;
	private String codeTitle;
	private String codeDel;
	private String codeImage;
	
	private String stackName;
	
	private String name;
	private String value;
	
	
	private MultipartFile[] profileFile;
	
	private String fileCode  ;    //글과 파일에 공통으로 추가되는 코드
	
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
