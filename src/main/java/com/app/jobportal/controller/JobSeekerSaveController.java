package com.app.jobportal.controller;

import com.app.jobportal.entity.JobPostActivity;
import com.app.jobportal.entity.JobSeekerProfile;
import com.app.jobportal.entity.JobSeekerSave;
import com.app.jobportal.entity.Users;
import com.app.jobportal.services.JobPostActivityService;
import com.app.jobportal.services.JobSeekerProfileService;
import com.app.jobportal.services.JobSeekerSaveService;
import com.app.jobportal.services.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobSeekerSaveController {

    private final UsersService usersService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobPostActivityService jobPostActivityService;

    public JobSeekerSaveController(UsersService usersService, JobSeekerSaveService jobSeekerSaveService, JobSeekerProfileService jobSeekerProfileService, JobPostActivityService jobPostActivityService) {
        this.usersService = usersService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, JobSeekerSave jobSeekerSave) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = usersService.getUserByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user."));
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
            if(seekerProfile.isPresent() && jobPostActivity != null) {
                jobSeekerSave.setJob(jobPostActivity);
                jobSeekerSave.setUserId(seekerProfile.get());
            }
            else {
                throw new RuntimeException("User not found");
            }
            jobSeekerSaveService.addNew(jobSeekerSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-jobs/")
    public String savedJobs(Model model) {
        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);
        for(JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
            jobPost.add(jobSeekerSave.getJob());
        }

        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-jobs";
    }
}
