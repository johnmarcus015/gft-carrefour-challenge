package com.ztx.skeleton.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: UserResponse,
    val private: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String? = null,
    val fork: Boolean,
    val url: String,
    @SerializedName("archive_url")
    val archiveUrl: String,
    @SerializedName("assignees_url")
    val assigneesUrl: String,
    @SerializedName("blobs_url")
    val blobsUrl: String,
    @SerializedName("branches_url")
    val branchesUrl: String,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("commits_url")
    val commitsUrl: String,
    @SerializedName("compare_url")
    val compareUrl: String,
    @SerializedName("contents_url")
    val contentsUrl: String,
    @SerializedName("contributors_url")
    val contributorsUrl: String,
    @SerializedName("deployments_url")
    val deploymentsUrl: String,
    @SerializedName("downloads_url")
    val downloadsUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("forks_url")
    val forksUrl: String,
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String,
    @SerializedName("git_refs_url")
    val gitRefsUrl: String,
    @SerializedName("git_tags_url")
    val gitTagsUrl: String,
    @SerializedName("git_url")
    val gitUrl: String,
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String,
    @SerializedName("issue_events_url")
    val issueEventsUrl: String,
    @SerializedName("issues_url")
    val issuesUrl: String,
    @SerializedName("keys_url")
    val keysUrl: String,
    @SerializedName("labels_url")
    val labelsUrl: String,
    @SerializedName("languages_url")
    val languagesUrl: String,
    @SerializedName("merges_url")
    val mergesUrl: String,
    @SerializedName("milestones_url")
    val milestonesUrl: String,
    @SerializedName("notifications_url")
    val notificationsUrl: String,
    @SerializedName("pulls_url")
    val pullsUrl: String,
    @SerializedName("releases_url")
    val releasesUrl: String,
    @SerializedName("ssh_url")
    val sshUrl: String,
    @SerializedName("stargazers_url")
    val stargazersUrl: String,
    @SerializedName("statuses_url")
    val statusesUrl: String,
    @SerializedName("subscribers_url")
    val subscribersUrl: String,
    @SerializedName("subscription_url")
    val subscriptionUrl: String,
    @SerializedName("tags_url")
    val tagsUrl: String,
    @SerializedName("teams_url")
    val teamsUrl: String,
    @SerializedName("trees_url")
    val treesUrl: String,
    @SerializedName("clone_url")
    val cloneUrl: String,
    @SerializedName("mirror_url")
    val mirrorUrl: String,
    @SerializedName("hooks_url")
    val hooksUrl: String,
    @SerializedName("svn_url")
    val svnUrl: String,
    val homepage: String? = null,
    val language: String? = null,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    val size: Int,
    @SerializedName("default_branch")
    val defaultBranch: String,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("is_template")
    val isTemplate: Boolean,
    val topics: List<String>, // is it correct?
    @SerializedName("has_issues")
    val hasIssues: Boolean,
    @SerializedName("has_projects")
    val hasProjects: Boolean,
    @SerializedName("has_wiki")
    val hasWiki: Boolean,
    @SerializedName("has_pages")
    val hasPages: Boolean,
    @SerializedName("has_downloads")
    val hasDownloads: Boolean,
    @SerializedName("has_discussions")
    val hasDiscussions: Boolean,
    val archived: Boolean,
    val disabled: Boolean,
    val visibility: String,
    @SerializedName("pushed_at")
    val pushedAt: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    val permissions: PermissionsResponse, // is it correct?
    @SerializedName("role_name")
    val roleName: String,
    @SerializedName("temp_clone_token")
    val tempCloneToken: String,
    @SerializedName("delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean,
    @SerializedName("subscribers_count")
    val subscribersCount: Int,
    @SerializedName("network_count")
    val networkCount: Int,
    @SerializedName("code_of_conduct")
    val codeOfConduct: CodeOfConductResponse,
    val license: LicenseResponse? = null,
    val forks: Int,
    @SerializedName("open_issues")
    val openIssues: Int,
    val watchers: Int,
    @SerializedName("allow_forking")
    val allowForking: Boolean,
    @SerializedName("web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean,
    @SerializedName("security_and_analysis")
    val securityAndAnalysis: SecurityAndAnalysisResponse? = null,
)

data class PermissionsResponse(
    val properties: PropertiesResponse
)

data class PropertiesResponse(
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
)

data class CodeOfConductResponse(
    val title: String,
    val description: String,
    val type: String,
    val properties: CodeOfConductPropertiesResponse
)

data class CodeOfConductPropertiesResponse(
    val key: String,
    val name: String,
    val url: String,
    val body: String,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
)

data class LicenseResponse(
    val properties: LicensePropertiesResponse
)

data class LicensePropertiesResponse(
    val key: String,
    val name: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    val url: String,
    @SerializedName("node_id")
    val nodeId: String
)

data class SecurityAndAnalysisResponse(
    val properties: SecurityAndAnalysisPropertiesResponse
)

data class SecurityAndAnalysisPropertiesResponse(
    @SerializedName("advanced_security")
    val advancedSecurity: AdvancedSecurityResponse,
//    @SerializedName("dependabot_security_updates")
//    val dependabotSecurityUpdates:
)

data class AdvancedSecurityResponse(
    val properties: AdvancedSecurityPropertiesResponse
)

data class AdvancedSecurityPropertiesResponse(
    val status: AdvancedSecurityPropertiesStatusResponse
)

enum class AdvancedSecurityPropertiesStatusResponse(val value: String) {
    ENABLED("enabled"), DISABLED("disabled")
}
