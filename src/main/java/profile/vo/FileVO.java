package profile.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 프로그램 설명
 * @date        : 2024. 3. 12.
 * @author      : boyoung
 * @version	: 1.0
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2024. 3. 12. boyoung : 최초작성
 * </PRE>
 */

@Data
@EqualsAndHashCode(of="fileCode")
public class FileVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private MultipartFile profileFile;
	
	private int rnum;
	
	private String fileCode       ;    //게시판과 파일의 공통코드
	private String fileName       ;
	private String fileSn         ;		//파일의 저장이름
	private String fileMime       ;
	private long fileSize       ;
	private String fileFancysize  ;
	private int fileNo; 				//파일의순번 
	private String filePath ; // 파일의 경로 
	private LocalDateTime fileRdate;
 	private String showProfileimg ;
	
	
	//파일을 지정된 폴더에 저장
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(profileFile != null) {
			profileFile.transferTo(new File(saveFolder, fileSn));
		}
	}
	
	public FileVO() {};
	
	
	public FileVO(MultipartFile profileFile) {
		super();
		this.profileFile = profileFile;
		this.fileName = profileFile.getOriginalFilename();
		this.fileSn = UUID.randomUUID().toString();
		this.fileMime = profileFile.getContentType();
		this.fileSize = profileFile.getSize();
		this.fileFancysize = FileUtils.byteCountToDisplaySize(fileSize);
	}

}
