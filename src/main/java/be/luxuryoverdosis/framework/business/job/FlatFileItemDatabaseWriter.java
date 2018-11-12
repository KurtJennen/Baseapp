package be.luxuryoverdosis.framework.business.job;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.WriteFailedException;
import org.springframework.batch.item.WriterNotOpenException;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.util.ExecutionContextUserSupport;
import org.springframework.batch.support.transaction.TransactionAwareBufferedWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import be.luxuryoverdosis.framework.business.service.interfaces.JobService;
import be.luxuryoverdosis.framework.data.to.Job;

public class FlatFileItemDatabaseWriter<T> extends ExecutionContextUserSupport implements ResourceAwareItemWriterItemStream<T>,
		InitializingBean {
	
	//added by luxuryoverdosis
	@Resource
	JobService jobService;
	
	//added by luxuryoverdosis
	private int jobId;

	private static final boolean DEFAULT_TRANSACTIONAL = true;

	protected static final Log logger = LogFactory.getLog(FlatFileItemWriter.class);

	private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

	private static final String WRITTEN_STATISTICS_NAME = "written";

	private static final String RESTART_DATA_NAME = "current.count";

	//commented by luxury overdosis
	//private org.springframework.core.io.Resource resource;

	private OutputState state = null;

	private LineAggregator<T> lineAggregator;

	private boolean saveState = true;

	//commented by luxury overdosis
	//private boolean shouldDeleteIfExists = true;

	//commented by luxury overdosis
	//private boolean shouldDeleteIfEmpty = false;

	private String encoding = OutputState.DEFAULT_CHARSET;

	private FlatFileHeaderCallback headerCallback;

	private FlatFileFooterCallback footerCallback;

	private String lineSeparator = DEFAULT_LINE_SEPARATOR;
	
	private boolean transactional = DEFAULT_TRANSACTIONAL;

	public FlatFileItemDatabaseWriter() {
		setName(ClassUtils.getShortName(FlatFileItemDatabaseWriter.class));
	}
	
	//added by luxuryoverdosis
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * Assert that mandatory properties (lineAggregator) are set.
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(lineAggregator, "A LineAggregator must be provided.");
	}

	/**
	 * Public setter for the line separator. Defaults to the System property
	 * line.separator.
	 * @param lineSeparator the line separator to set
	 */
	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	/**
	 * Public setter for the {@link LineAggregator}. This will be used to
	 * translate the item into a line for output.
	 * 
	 * @param lineAggregator the {@link LineAggregator} to set
	 */
	public void setLineAggregator(LineAggregator<T> lineAggregator) {
		this.lineAggregator = lineAggregator;
	}

	
	/**
	 * Setter for resource. Represents a file that can be written.
	 * 
	 * @param resource
	 */
	public void setResource(org.springframework.core.io.Resource resource) {
		//this.resource = resource;
	}

	/**
	 * Sets encoding for output template.
	 */
	public void setEncoding(String newEncoding) {
		this.encoding = newEncoding;
	}

	//commented by luxury overdosis
//	/**
//	 * Flag to indicate that the target file should be deleted if it already
//	 * exists, otherwise it will be appended. If headers are emitted then
//	 * appending will cause them to show up in the middle of the file. Defaults
//	 * to true (so no appending except on restart).
//	 * 
//	 * @param shouldDeleteIfExists the flag value to set
//	 */
//	public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
//		this.shouldDeleteIfExists = shouldDeleteIfExists;
//	}

	//commented by luxury overdosis
//	/**
//	 * Flag to indicate that the target file should be deleted if no lines have
//	 * been written (other than header and footer) on close. Defaults to false.
//	 * 
//	 * @param shouldDeleteIfEmpty the flag value to set
//	 */
//	public void setShouldDeleteIfEmpty(boolean shouldDeleteIfEmpty) {
//		this.shouldDeleteIfEmpty = shouldDeleteIfEmpty;
//	}

	/**
	 * Set the flag indicating whether or not state should be saved in the
	 * provided {@link ExecutionContext} during the {@link ItemStream} call to
	 * update. Setting this to false means that it will always start at the
	 * beginning on a restart.
	 * 
	 * @param saveState
	 */
	public void setSaveState(boolean saveState) {
		this.saveState = saveState;
	}

	/**
	 * headerCallback will be called before writing the first item to file.
	 * Newline will be automatically appended after the header is written.
	 */
	public void setHeaderCallback(FlatFileHeaderCallback headerCallback) {
		this.headerCallback = headerCallback;
	}

	/**
	 * footerCallback will be called after writing the last item to file, but
	 * before the file is closed.
	 */
	public void setFooterCallback(FlatFileFooterCallback footerCallback) {
		this.footerCallback = footerCallback;
	}

	/**
	 * Flag to indicate that writing to the buffer should be delayed if a
	 * transaction is active. Defaults to true.
	 */
	public void setTransactional(boolean transactional) {
		this.transactional = transactional;
	}

	/**
	 * Writes out a string followed by a "new line", where the format of the new
	 * line separator is determined by the underlying operating system. If the
	 * input is not a String and a converter is available the converter will be
	 * applied and then this method recursively called with the result. If the
	 * input is an array or collection each value will be written to a separate
	 * line (recursively calling this method for each value). If no converter is
	 * supplied the input object's toString method will be used.<br/>
	 * 
	 * @param items list of items to be written to output stream
	 * @throws Exception if the transformer or file output fail,
	 * WriterNotOpenException if the writer has not been initialized.
	 */
	public void write(List<? extends T> items) throws Exception {

		if (!getOutputState().isInitialized()) {
			throw new WriterNotOpenException("Writer must be open before it can be written to");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Writing to flat file with " + items.size() + " items.");
		}

		OutputState state = getOutputState();

		StringBuilder lines = new StringBuilder();
		int lineCount = 0;
		for (T item : items) {
			lines.append(lineAggregator.aggregate(item) + lineSeparator);
			lineCount++;
		}
		try {
			state.write(lines.toString());
		}
		catch (IOException e) {
			throw new WriteFailedException("Could not write data.  The file may be corrupt.", e);
		}
		state.linesWritten += lineCount;
	}

	/**
	 * @see ItemStream#close()
	 */
	public void close() {
		if (state != null) {
			try {
				if (footerCallback != null && state.outputBufferedWriter != null) {
					footerCallback.writeFooter(state.outputBufferedWriter);
					state.outputBufferedWriter.flush();
				}
			}
			catch (IOException e) {
				throw new ItemStreamException("Failed to write footer before closing", e);
			}
			finally {
				state.close();
				//commented by luxury overdosis
//				if (state.linesWritten == 0 && shouldDeleteIfEmpty) {
//					try {
//						resource.getFile().delete();
//					}
//					catch (IOException e) {
//						throw new ItemStreamException("Failed to delete empty file on close", e);
//					}
//				}
				state = null;
			}
		}
	}

	/**
	 * Initialize the reader. This method may be called multiple times before
	 * close is called.
	 * 
	 * @see ItemStream#open(ExecutionContext)
	 */
	public void open(ExecutionContext executionContext) throws ItemStreamException {

		//commented by luxury overdosis
		//Assert.notNull(resource, "The resource must be set");

		if (!getOutputState().isInitialized()) {
			doOpen(executionContext);
		}
	}

	private void doOpen(ExecutionContext executionContext) throws ItemStreamException {
		OutputState outputState = getOutputState();
		if (executionContext.containsKey(getKey(RESTART_DATA_NAME))) {
			outputState.restoreFrom(executionContext);
		}
		try {
			outputState.initializeBufferedWriter();
		}
		catch (IOException ioe) {
			throw new ItemStreamException("Failed to initialize writer", ioe);
		}
		if (outputState.lastMarkedByteOffsetPosition == 0) {
			if (headerCallback != null) {
				try {
					headerCallback.writeHeader(outputState.outputBufferedWriter);
					outputState.write(lineSeparator);
				}
				catch (IOException e) {
					throw new ItemStreamException("Could not write headers.  The file may be corrupt.", e);
				}
			}
		}
	}

	/**
	 * @see ItemStream#update(ExecutionContext)
	 */
	public void update(ExecutionContext executionContext) {
		if (state == null) {
			throw new ItemStreamException("ItemStream not open or already closed.");
		}

		Assert.notNull(executionContext, "ExecutionContext must not be null");

		if (saveState) {

			try {
				executionContext.putLong(getKey(RESTART_DATA_NAME), state.position());
			}
			catch (IOException e) {
				throw new ItemStreamException("ItemStream does not return current position properly", e);
			}

			executionContext.putLong(getKey(WRITTEN_STATISTICS_NAME), state.linesWritten);
		}
	}

	// Returns object representing state.
	private OutputState getOutputState() {
		if (state == null) {
			//commented by luxury overdosis
//			File file;
//			try {
//				file = resource.getFile();
//			}
//			catch (IOException e) {
//				throw new ItemStreamException("Could not convert resource to file: [" + resource + "]", e);
//			}
//			Assert.state(!file.exists() || file.canWrite(), "Resource is not writable: [" + resource + "]");
			state = new OutputState();
			//commented by luxury overdosis
//			state.setDeleteIfExists(shouldDeleteIfExists);
			state.setEncoding(encoding);
		}
		return state;
	}

	/**
	 * Encapsulates the runtime state of the writer. All state changing
	 * operations on the writer go through this class.
	 */
	private class OutputState {
		// default encoding for writing to output files - set to UTF-8.
		private static final String DEFAULT_CHARSET = "UTF-8";

		//commented by luxury overdosis
		//private FileOutputStream os;
		
		//added by luxury overdosis
		private ByteArrayOutputStream baos;

		// The bufferedWriter over the file channel that is actually written
		Writer outputBufferedWriter;

		FileChannel fileChannel;

		// this represents the charset encoding (if any is needed) for the
		// output file
		String encoding = DEFAULT_CHARSET;

		//commented by luxury overdosis
		//boolean restarted = false;

		long lastMarkedByteOffsetPosition = 0;

		long linesWritten = 0;

		//commented by luxury overdosis
		//boolean shouldDeleteIfExists = true;

		boolean initialized = false;

		/**
		 * Return the byte offset position of the cursor in the output file as a
		 * long integer.
		 */
		public long position() throws IOException {
			long pos = 0;

			if (fileChannel == null) {
				return 0;
			}

			outputBufferedWriter.flush();
			pos = fileChannel.position();
			if (transactional) {
				pos += ((TransactionAwareBufferedWriter) outputBufferedWriter).getBufferSize();
			}

			return pos;

		}

		/**
		 * @param executionContext
		 */
		public void restoreFrom(ExecutionContext executionContext) {
			lastMarkedByteOffsetPosition = executionContext.getLong(getKey(RESTART_DATA_NAME));
			//commented by luxury overdosis
			//restarted = true;
		}

		//commented by luxury overdosis
//		/**
//		 * @param shouldDeleteIfExists
//		 */
//		public void setDeleteIfExists(boolean shouldDeleteIfExists) {
//			this.shouldDeleteIfExists = shouldDeleteIfExists;
//		}

		/**
		 * @param encoding
		 */
		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		/**
		 * Close the open resource and reset counters.
		 */
		public void close() {

			initialized = false;
			//commented by luxury overdosis
			//restarted = false;
			try {
				if (outputBufferedWriter != null) {
					//added by luxuryoverdosis
					
					Job job = jobService.read(jobId);
					job.setFileData(baos.toByteArray());
					job.setFileSize(baos.size());
					
					jobService.createOrUpdate(job);
					
					//outputBufferedWriter.close();
				}
			}
//			catch (IOException ioe) {
//				throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
//			}
			finally {
				if (!transactional) {
					closeStream();
				}
			}
		}

		private void closeStream() {
//			try {
//				if (fileChannel != null) {
//					fileChannel.close();
//				}
//			}
//			catch (IOException ioe) {
//				throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
//			}
//			finally {
				try {
					if (baos != null) {
						baos.close();
					}
				}
				catch (IOException ioe) {
					throw new ItemStreamException("Unable to close the the ItemWriter", ioe);
				}
//			}
		}

		/**
		 * @param line
		 * @throws IOException
		 */
		public void write(String line) throws IOException {
			if (!initialized) {
				initializeBufferedWriter();
			}

			//commented by luxury overdosis
//			outputBufferedWriter.write(line);
//			outputBufferedWriter.flush();
			
			//added by luxury overdosis
			baos.write(line.getBytes());
			baos.flush();
		}

		//commented by luxury overdosis
//		/**
//		 * Truncate the output at the last known good point.
//		 * 
//		 * @throws IOException
//		 */
//		public void truncate() throws IOException {
//			fileChannel.truncate(lastMarkedByteOffsetPosition);
//			fileChannel.position(lastMarkedByteOffsetPosition);
//		}

		/**
		 * Creates the buffered writer for the output file channel based on
		 * configuration information.
		 * @throws IOException
		 */
		private void initializeBufferedWriter() throws IOException {

			//commented by luxury overdosis
			//File file = resource.getFile();

			//commented by luxury overdosis
			//FileUtils.setUpOutputFile(file, restarted, shouldDeleteIfExists);

			//commented by luxury overdosis
			//os = new FileOutputStream(file.getAbsolutePath(), true);
			//fileChannel = os.getChannel();
			
			//added by luxury overdosis
			baos = new ByteArrayOutputStream();
			
			WritableByteChannel fileChannel = Channels.newChannel(baos);

			outputBufferedWriter = getBufferedWriter(fileChannel, encoding);

			Assert.state(outputBufferedWriter != null, "Object outputBufferedWriter is empty");
			
			//commented by luxury overdosis
			// in case of restarting reset position to last committed point
//			if (restarted) {
//				checkFileSize();
//				truncate();
//			}

			initialized = true;
			linesWritten = 0;
		}

		public boolean isInitialized() {
			return initialized;
		}

		/**
		 * Returns the buffered writer opened to the beginning of the file
		 * specified by the absolute path name contained in absoluteFileName.
		 */
		private Writer getBufferedWriter(WritableByteChannel fileChannel, String encoding) {
			try {
				Writer writer = Channels.newWriter(fileChannel, encoding);
//				if (transactional) {
//					return new TransactionAwareBufferedWriter(fileChannel, new Runnable() {
//						public void run() {
//							closeStream();
//						}
//					});
//				} else {
					return new BufferedWriter(writer);
//				}
			}
			catch (UnsupportedCharsetException ucse) {
				throw new ItemStreamException("Bad encoding configuration for output file " + fileChannel, ucse);
			}
		}

		//commented by luxury overdosis
//		/**
//		 * Checks (on setState) to make sure that the current output file's size
//		 * is not smaller than the last saved commit point. If it is, then the
//		 * file has been damaged in some way and whole task must be started over
//		 * again from the beginning.
//		 * @throws IOException if there is an IO problem
//		 */
//		private void checkFileSize() throws IOException {
//			long size = -1;
//
//			outputBufferedWriter.flush();
//			size = fileChannel.size();
//
//			if (size < lastMarkedByteOffsetPosition) {
//				throw new ItemStreamException("Current file size is smaller than size at last commit");
//			}
//		}

	}

}
