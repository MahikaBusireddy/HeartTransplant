int executeBuiltIn( command *cmd ) {
	const char *home_dir = getenv("HOME");
	if (home_dir != NULL){
			printf("Changing to the home directory: %s\n", home_dir);
			if (chdir(home_dir) != 0){
				fprintf(stderr, "cd: Failed to change to the home directory\n");
			}
	} else {
			fprintf(stderr, "cd: Home environment variable not set\n");
		}
		
	if ( strcmp( cmd -> args[ 0 ] , "cd" ) == 0 ){
		char *token = cmd -> args[ 1 ];

		if ( cmd -> argCount <= 1 ) {
			fprintf( stderr , "cd: expected arguement\n");
			return 1;
		} else if( strcmp( token , "-" ) == 0 ){
			// change to the previous working directory
			char *prev_dir = getenv( "previous working directory" );
			if ( prev_dir != NULL ) {
				if ( chdir( prev_dir ) != 0 ) {
					fprintf( stderr , "cd: failed to changed the directory to '%s'\n" , prev_dir );
					return 1;
				}
			} else {
				fprintf( stderr , "cd: old pwd environment variable not set\n" );
				return 1;
			}
		} else {
			printf( "Changing to directory: %s\n" , token );
			if ( chdir( token ) != 0 ){
				fprintf( stderr , "cd: Failed to change the directory to '%s'\n" , token );
			}
		} 
		char current_dir[MAX_PATH_LENGTH];
		if (getcwd(current_dir, sizeof(current_dir)) != NULL){
			printf("Current directory: %s\n", current_dir);
		} else {
			perror("getcwd");
		}            
	} 
	else if ( strcmp( cmd -> args[ 0 ] , "pwd" ) == 0 ){
		char *token = cmd -> args[ 1 ];
		
		if (strcmp(token, "pwd") == 0){
			char current_dir[MAX_PATH_LENGTH];
			if (getcwd(current_dir, sizeof(current_dir)) != NULL){
				printf("Current directory: %s\n", current_dir);
			} else {
				perror("getcwd");
			}
		} 
	}
	else if ( strcmp( cmd -> args[ 0 ] , "which" ) == 0 ){
		char *token = cmd -> args[ 1 ];
		if (strcmp(token, "which") == 0) {
			token = strtok(NULL, " \t\n");
			if (token != NULL) {
				if (strcmp(token, "cd") == 0 || strcmp(token, "pwd") == 0 || strcmp(token, "which") == 0){
					fprintf(stderr, "which: %s is a built in command\n",token);
				} else {
					char *path_env = getenv("PATH");
					if (path_env != NULL){
						char *path = strtok(path_env, ":");
						while (path != NULL){
							char program_path[MAX_PATH_LENGTH];
							snprintf(program_path, sizeof(program_path), "%s/%s", path, token);
							if (access(program_path, X_OK) == 0){
								printf("%s\n", program_path);
								return;
							}
							path = strtok(NULL, ":");
						}
						fprintf(stderr, "which: %s not found in PATH\n", token);
					} else {
						fprintf(stderr, "which: PATH environment variable not set\n");
					}
				}
			} else {
				fprintf(stderr, "which: Missing argument\n");
			}
		} 
	}
	else if ( strcmp( cmd -> args[ 0 ] , "ls" ) == 0 ){
		char *token = cmd -> args[ 1 ];
		if( strcmp(token, "ls") == 0){
			char current_dir[MAX_PATH_LENGTH];
			if (getcwd(current_dir, sizeof(current_dir)) != NULL){
				char *ls_args[] = {"ls", current_dir, NULL};
				execute_command(ls_args, NULL, NULL);
			} else {
				perror("getcwd");
			}
		} 
	}
	else {
		args[arg_count++] = token;
		if (arg_count >= MAX_ARG_COUNT -1){
			fprintf(stderr, "Too many arguments\n");
			exit(EXIT_FAILURE);
		}
	}
}